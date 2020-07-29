package com.heu.finance.controller.admin.loan;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.loan.LoanInfo;
import com.heu.finance.pojo.loan.LoanInfoRemindPay;
import com.heu.finance.pojo.personal.SendInfo;
import com.heu.finance.service.loan.LoanInfoService;
import com.heu.finance.service.personal.MyInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 对应 admin端 网贷信息界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class LoanInfoController {
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private MyInfoService myInfoService;

    /**
     * 展示admin端的零钱理财管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
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
        model.addAttribute("activeUrl2","loaninfoActive");
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/loan/loaninfo";
    }

    /**
     * 根据网贷项ID通知还款
     * @param id ID
     * @return Msg
     */
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
