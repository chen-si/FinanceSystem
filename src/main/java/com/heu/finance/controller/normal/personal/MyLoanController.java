package com.heu.finance.controller.normal.personal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.personal.MyLoan;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.loan.LoanExamService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户我的借贷页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class MyLoanController {
    @Autowired
    private LoanExamService loanExamService;

    /**
     * 展示我的借贷页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/myloan")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "orderBy",defaultValue = "default") String orderBy,
                                       Model model) {
        if (orderBy.equals("")){
            orderBy = "default";
        }

        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);

        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        List<MyLoan> list = loanExamService.myLoanOrderBy(user.getId(),orderBy);

        //pageinfo封装分页信息
        PageInfo<MyLoan> pageInfo = new PageInfo<MyLoan>(list);
        model.addAttribute("myLoansPageInfo", pageInfo);
        model.addAttribute("loansList", list);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "myLoanActive");

        model.addAttribute("orderBy",orderBy);

        model.addAttribute("session", session);
        return "user/personal/myloan";

    }


    /**
     * 还款
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/repayment/{id}")
    @ResponseBody
    public Msg repayment(@PathVariable("id") Integer id){
        int i = loanExamService.repayLoan(id);
        if (i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }


}
