package com.heu.finance.controller.admin.loan;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.loan.LoanInfo;
import com.heu.finance.pojo.loan.LoanInfoRemindPay;
import com.heu.finance.pojo.personal.SendInfo;
import com.heu.finance.service.admin.loan.LoanInfoService;
import com.heu.finance.service.normal.personal.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoanInfoController {
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private MyInfoService myInfoService;

    @RequestMapping("/loanInfoList")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "orderBy" , defaultValue = "default") String orderBy,
                                       Model model) {

        //防止orderBy空设置
        if (orderBy.equals("")){
            orderBy = "default";
        }

        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<LoanInfo> list = loanInfoService.selectLoanInfoOrderBy(orderBy);

        //pageinfo封装分页信息
        PageInfo<LoanInfo> pageInfo = new PageInfo<LoanInfo>(list);
        model.addAttribute("loanPageInfo",pageInfo);
        model.addAttribute("loanList",list);
        model.addAttribute("orderBy",orderBy);

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
        SendInfo sendInfo = new SendInfo();
        Date date = new Date();
        LoanInfoRemindPay loanInfoRemindPay = loanInfoService.selectById(id);
        sendInfo.setReceiveId(loanInfoRemindPay.getLoanId());
        sendInfo.setCreateTime(date);
        sendInfo.setTitle("网贷还款通知！");
        sendInfo.setUsername(loanInfoRemindPay.getUsername());
        sendInfo.setAmount(loanInfoRemindPay.getAmount());

        int i = myInfoService.remindpay(sendInfo);
        if (i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }

    }
}
