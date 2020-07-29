package com.heu.finance.controller.admin.loan;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.loan.LoanExam;
import com.heu.finance.pojo.loan.LoanInfoRemindPay;
import com.heu.finance.pojo.personal.SendInfo;
import com.heu.finance.service.loan.LoanExamService;
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
 * 对应 admin端 网贷审核界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class LoanExamController {
    @Autowired
    private LoanExamService loanExamService;
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private MyInfoService myInfoService;

    /**
     * 展示admin端的网贷审核页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/loanExamList")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "orderBy",defaultValue = "default") String orderBy,
                                       Model model) {
        //防止orderBy空设置
        if (orderBy.equals("")){
            orderBy = "default";
        }

        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<LoanExam> list = loanExamService.selectLoanExamOrderBy(orderBy);


        //pageinfo封装分页信息
        PageInfo<LoanExam> pageInfo = new PageInfo<LoanExam>(list);

        model.addAttribute("loanPageInfo", pageInfo);
        model.addAttribute("loanList", list);
        model.addAttribute("orderBy",orderBy);

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "loanActive");
        model.addAttribute("activeUrl2", "loanexamActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/loan/loanexam";
    }


    /**
     * 对应ID的网贷项审核通过
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/passApplyStatus/{id}")
    @ResponseBody
    public Msg passApplyStatus(@PathVariable("id") Integer id){
        LoanExam loanExam = new LoanExam();
        loanExam.setId(id);
        int i = loanExamService.updateApplyStatus(loanExam);
        //发送通知
        SendInfo sendInfo = new SendInfo();
        Date date = new Date();
        LoanInfoRemindPay loanInfoRemindPay = loanInfoService.selectById(id);
        sendInfo.setReceiveId(loanInfoRemindPay.getLoanId());
        sendInfo.setCreateTime(date);
        sendInfo.setTitle("网贷审核通过");
        sendInfo.setUsername(loanInfoRemindPay.getUsername());
        sendInfo.setAmount(loanInfoRemindPay.getAmount());

        int m = myInfoService.passExam(sendInfo);
        if (i==1 && m==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

    /**
     * 对应ID的网贷项审核不通过
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/notPassApplyStatus/{id}")
    @ResponseBody
    public Msg notPassApplyStatus(@PathVariable("id") Integer id){
        LoanExam loanExam1 = new LoanExam();
        loanExam1.setId(id);
        int j = loanExamService.updateApplyStausNotPass(loanExam1);
        //发送通知
        SendInfo sendInfo1 = new SendInfo();
        Date date = new Date();
        LoanInfoRemindPay loanInfoRemindPay = loanInfoService.selectById(id);
        sendInfo1.setReceiveId(loanInfoRemindPay.getLoanId());
        sendInfo1.setCreateTime(date);
        sendInfo1.setTitle("网贷审核未通过");
        sendInfo1.setUsername(loanInfoRemindPay.getUsername());
        sendInfo1.setAmount(loanInfoRemindPay.getAmount());

        int n = myInfoService.notPassExam(sendInfo1);
        if (j==1 && n == 1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

}
