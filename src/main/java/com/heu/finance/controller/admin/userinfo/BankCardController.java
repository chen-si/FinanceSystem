package com.heu.finance.controller.admin.userinfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.admin.userinfo.BankCard;
import com.heu.finance.pojo.admin.userinfo.User;
import com.heu.finance.service.admin.userinfo.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BankCardController {
    private BankCardService bankCardService;

    @Autowired
    public void setBankCardService(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }
    @RequestMapping("/bankCardList")
    public String selectAllBankCard(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                    Model model, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);

        List<BankCard> list = bankCardService.selectAllBankCard();
        while(list.isEmpty() && !pageNum.equals(1)){
            PageHelper.startPage(pageNum - 1,pageSize);
            list = bankCardService.selectAllBankCard();
        }
        //System.out.println(list);
        PageInfo<BankCard> pageInfo = new PageInfo<>(list);
        //System.out.println(list);
        model.addAttribute("bankcardPageInfo",pageInfo);
        model.addAttribute("bankcardList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","userInfoActive");
        model.addAttribute("activeUrl2","bankcardActive");
        model.addAttribute("username",request.getSession().getAttribute("username"));

        return "admin/userinfo/bankcard";
    }

    @RequestMapping("/getBankCardById/{id}")
    @ResponseBody
    public Msg getUserById(@PathVariable("id") Integer id){
        BankCard bankCard = bankCardService.getBankCardById(id);
        if (bankCard != null){
            return Msg.success().add("bankCard",bankCard);
        }
        return Msg.failed();
    }

    @RequestMapping("/updateBankCard/{id}")
    @ResponseBody
    public Msg updateUserProfile(@PathVariable("id") Integer id,
                                 @RequestParam(value = "cardBank") String cardBank,
                                 @RequestParam(value = "type") Integer type,
                                 @RequestParam(value = "cardNum") String cardNum){
        BankCard bankCard = new BankCard();
        bankCard.setId(id);
        bankCard.setCardBank(cardBank);
        bankCard.setCardNum(cardNum);
        bankCard.setType(type);

        if(bankCardService.updateBankCardInfos(bankCard) == 1){
            return Msg.success();
        }
        return Msg.failed();

    }

    @RequestMapping("/deleteBankCard/{id}")
    @ResponseBody
    public Msg deleteUserById(@PathVariable("id") Integer id){
        if(bankCardService.deleteBankCardById(id) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }
}

