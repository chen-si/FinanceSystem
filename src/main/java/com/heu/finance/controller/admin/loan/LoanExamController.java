package com.heu.finance.controller.admin.loan;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.loan.LoanExam;
import com.heu.finance.service.admin.loan.LoanExamService;
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
public class LoanExamController {

    @Autowired
    private LoanExamService loanExamService;

    @RequestMapping("/loanExamList")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       Model model) {
        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<LoanExam> list = loanExamService.selectLoanExamAll();

        System.out.println(list);

        //pageinfo封装分页信息
        PageInfo<LoanExam> pageInfo = new PageInfo<LoanExam>(list);

        model.addAttribute("loanPageInfo", pageInfo);
        model.addAttribute("loanList", list);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "loanActive");
        model.addAttribute("activeUrl2", "loanexamActive");

        model.addAttribute("username", "username");
        return "admin/loan/loanexam";

    }

    //审核通过
    @RequestMapping("/passApplyStatus/{id}")
    @ResponseBody
    public Msg passApplyStatus(@PathVariable("id") Integer id){
        LoanExam loanExam = new LoanExam();
        loanExam.setId(id);
        int i = loanExamService.updateApplyStatus(loanExam);
        if (i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

    //审核不通过
    @RequestMapping("/notPassApplyStatus/{id}")
    @ResponseBody
    public Msg notPassApplyStatus(@PathVariable("id") Integer id){
        LoanExam loanExam1 = new LoanExam();
        loanExam1.setId(id);
        int j = loanExamService.updateApplyStausNotPass(loanExam1);
        if (j==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }


}
