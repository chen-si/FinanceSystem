package com.heu.finance.controller.normal.personal;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.BankCard;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.admin.userinfo.BankCardService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserBankCardController {
    private BankCardService bankCardService;

    @Autowired
    public void setBankCardService(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    @RequestMapping("/bankCard")
    public String myBankCard(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        List<BankCard> bankCardList = bankCardService.getBankCardByUserId(user.getId());

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "bankCardActive");

        model.addAttribute("session",session);
        model.addAttribute("bankCardList",bankCardList);

        return "user/personal/bankcard";
    }

    @RequestMapping("/addBankCard")
    @ResponseBody
    public Msg addBankCard(BankCard bankCard){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");
//        System.out.println(bankCard);
        bankCardService.insertBankCard(bankCard,user.getId());
//        System.out.println(bankCard);
        if (bankCard.getId() != null){
            return Msg.success();
        }
        return Msg.failed();
    }

    @RequestMapping("/getBankCardById/{id}")
    @ResponseBody
    public Msg getBankCardById(@PathVariable("id") Integer id){
        BankCard bankCard = bankCardService.getBankCardById(id);

        if (bankCard != null){
            return Msg.success().add("bankcard",bankCard);
        }
        return Msg.failed();
    }

    @RequestMapping("/updateBankCard/{id}")
    @ResponseBody
    public Msg updateBankCard(@PathVariable("id") Integer id,
                              BankCard bankCard){
        System.out.println(bankCard);
        bankCard.setId(id);

        if ( bankCardService.updateBankCardInfos(bankCard) == 1){
            return Msg.success();
        }

        return Msg.failed();
    }

    @RequestMapping("/deleteBankCard/{id}")
    @ResponseBody
    public Msg deleteBankCard(@PathVariable("id") Integer id){
        if ( bankCardService.deleteBankCardById(id) == 1 ){
            return Msg.success();
        }
        return Msg.failed();
    }
}
