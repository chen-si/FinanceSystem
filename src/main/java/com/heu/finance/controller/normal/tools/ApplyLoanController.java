package com.heu.finance.controller.normal.tools;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.tools.UserApplyLoan;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.loan.LoanExamService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 网贷申请页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class ApplyLoanController {
    @Autowired
    private LoanExamService loanExamService;

    /**
     * 展示网贷申请页面
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/applyLoanList")
    public String apply(Model model){
        Session session = SecurityUtils.getSubject().getSession();

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "applyLoanActive");

        model.addAttribute("session", session);

        return "user/tools/applyloan";
    }

    /**
     * 申请网贷
     * @param amount amount
     * @param term term
     * @param rate rate
     * @return Msg
     */
    @RequestMapping("/applyLoan")
    @ResponseBody
    public Msg applyLoan(@RequestParam("amount") Double amount,
                         @RequestParam("term") Integer term,
                         @RequestParam("rate") Double rate){

        UserApplyLoan userApplyLoan = new UserApplyLoan();
        Date date = new Date();

        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        userApplyLoan.setLoanId(user.getId());
        userApplyLoan.setLoanTime(date);
        userApplyLoan.setAmount(amount);
        userApplyLoan.setTerm(term);
        userApplyLoan.setRate(rate);

        int i =loanExamService.applyloan(userApplyLoan);
        if (i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

}
