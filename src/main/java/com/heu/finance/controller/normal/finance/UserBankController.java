package com.heu.finance.controller.normal.finance;

import com.heu.finance.pojo.finance.Bank;
import com.heu.finance.service.admin.finance.BankService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserBankController {
    private BankService bankService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

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
