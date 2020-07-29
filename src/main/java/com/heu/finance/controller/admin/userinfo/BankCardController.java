package com.heu.finance.controller.admin.userinfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.BankCard;
import com.heu.finance.service.userinfo.BankCardService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对应 admin端 银行卡管理界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class BankCardController {
    private BankCardService bankCardService;

    @Autowired
    public void setBankCardService(BankCardService bankCardService) {
        this.bankCardService = bankCardService;
    }

    /**
     * 展示admin端的银行卡管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
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
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/userinfo/bankcard";
    }

    /**
     * 获取并返回修改银行卡管理回显数据
     * @param id 需要回显的银行卡项id
     * @return Msg
     */
    @RequestMapping("/getBankCardById/{id}")
    @ResponseBody
    public Msg getUserById(@PathVariable("id") Integer id){
        BankCard bankCard = bankCardService.getBankCardById(id);
        if (bankCard != null){
            return Msg.success().add("bankCard",bankCard);
        }
        return Msg.failed();
    }

    /**
     * 更新对应ID的银行卡信息
     * @param id ID
     * @param cardBank cardBank
     * @param type type
     * @param cardNum cardNum
     * @return Msg
     */
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

    /**
     * 删除对应ID的银行卡
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/deleteBankCard/{id}")
    @ResponseBody
    public Msg deleteUserById(@PathVariable("id") Integer id){
        if(bankCardService.deleteBankCardById(id) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }
}

