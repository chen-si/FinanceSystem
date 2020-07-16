package com.heu.finance.controller.admin.loan;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.loan.LoanInfo;
import com.heu.finance.service.admin.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoanInfoController {

    @Autowired
    private LoanInfoService loanInfoService;

    @RequestMapping("/loanInfoList")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       Model model) {
        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<LoanInfo> list = loanInfoService.selectLoanInfoAll();

        //pageinfo封装分页信息
        PageInfo<LoanInfo> pageInfo = new PageInfo<LoanInfo>(list);
        model.addAttribute("loanPageInfo",pageInfo);
        model.addAttribute("loanList",list);
        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","loanActive");
        model.addAttribute("activeUrl","loaninfoActive");
        model.addAttribute("username","username");
        return "admin/loan/loaninfo";

    }

    //提醒还款
    @RequestMapping("/remindPay/{id}")
    @ResponseBody
    public Msg remindPay(@PathVariable("id") Integer id){
        return Msg.success();
    }
}
