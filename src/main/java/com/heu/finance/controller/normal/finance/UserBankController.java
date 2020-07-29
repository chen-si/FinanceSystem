package com.heu.finance.controller.normal.finance;

import com.heu.finance.pojo.finance.Bank;
import com.heu.finance.service.finance.BankService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * 用户推荐银行
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class UserBankController {
    private BankService bankService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    /**
     * 显示推荐银行信息
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/bankList")
    public String selectBankAll(Model model){
        List<Bank> userBankList =bankService.selectBankAll();

        model.addAttribute("bankList", userBankList);

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "bankActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "user/finance/bank";
    }
}
