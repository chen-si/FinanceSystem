package com.heu.finance.controller.normal.tools;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.tools.UserApplyLoan;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.admin.loan.LoanExamService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class ApplyLoanController {

    @Autowired
    private LoanExamService loanExamService;

    @RequestMapping("/applyLoanList")
    public String apply(Model model){
        Session session = SecurityUtils.getSubject().getSession();

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "applyLoanActive");

        model.addAttribute("session", session);

        return "user/tools/applyloan";
    }

    @RequestMapping("/applyLoan")
    @ResponseBody
    public Msg applyLoan(@RequestParam("amount") Double amount,
                         @RequestParam("term") Integer term,
                         @RequestParam("rate") Double rate,
                         HttpServletRequest request){

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
