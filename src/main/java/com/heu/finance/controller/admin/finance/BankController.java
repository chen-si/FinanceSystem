package com.heu.finance.controller.admin.finance;

import com.demo.common.Msg;
import com.demo.pojo.Bank;
import com.demo.service.BankService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BankController {
    @Autowired
    private BankService bankService;

    @RequestMapping("/admin/finance/toBank.html")
    public String selectBankAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                Model model){
        //引入pagehelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<Bank> bankList = bankService.selectBankAll();
        //PageInfo封装分页信息
        PageInfo<Bank> pageInfo = new PageInfo<>(bankList);
        model.addAttribute("finacnePageInfo", pageInfo);
        model.addAttribute("financeList", bankList);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "bankctive");
        model.addAttribute("username", "username");

        return "admin/finance/bank";
    }

    @RequestMapping("/admin/addBank")
    @ResponseBody
    public Msg insertBank(Bank bank)
    {
        int i = bankService.addBank(bank);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @RequestMapping("/admin/getBankInfoById/{id}")
    @ResponseBody
    public Msg getBankInfoById(@PathVariable("id") Integer id){
        Bank bank = bankService.selectBankById(id);
        return Msg.success().add("bank", bank);
    }

    @RequestMapping("/admin/updateBank/{update-id}")
    @ResponseBody
    public Msg updateBank(Bank bank, @PathVariable("update-id") Integer id){
        bank.setId(id);
        int i = bankService.updateBank(bank);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    @RequestMapping("/admin/deleteBankById/{id}")
    @ResponseBody
    public Msg deleteBankById(@PathVariable("id") Integer id){
        int i =bankService.deleteBankById(id);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
