package com.heu.finance.controller.normal.personal;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.BankCard;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.userinfo.BankCardService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户银行卡信息页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class UserBankCardController {
    private BankCardService bankCardService;

    @Autowired
    public void setBankCardService(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    /**
     * 展示用户银行卡信息
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
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

    /**
     * 添加银行卡
     * @param bankCard bankCard
     * @return Msg
     */
    @RequestMapping("/addBankCard")
    @ResponseBody
    public Msg addBankCard(BankCard bankCard){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");
        if (bankCardService.insertBankCard(bankCard,user.getId()) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }

    /**
     * 修改对应ID的银行卡信息回显
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/getBankCardById/{id}")
    @ResponseBody
    public Msg getBankCardById(@PathVariable("id") Integer id){
        BankCard bankCard = bankCardService.getBankCardById(id);

        if (bankCard != null){
            return Msg.success().add("bankcard",bankCard);
        }
        return Msg.failed();
    }

    /**
     * 修改/关系对应ID银行卡信息
     * @param id ID
     * @param bankCard bankCard
     * @return Msg
     */
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

    /**
     * 删除对应ID的银行卡信息
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/deleteBankCard/{id}")
    @ResponseBody
    public Msg deleteBankCard(@PathVariable("id") Integer id){
        if ( bankCardService.deleteBankCardById(id) == 1 ){
            return Msg.success();
        }
        return Msg.failed();
    }
}
