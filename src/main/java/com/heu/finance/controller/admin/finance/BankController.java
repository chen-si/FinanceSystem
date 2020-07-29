package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.Bank;
import com.heu.finance.service.finance.BankService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 对应 admin端 精选银行界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class BankController {
    @Autowired
    private BankService bankService;

    /**
     * 显示推荐银行的管理界面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param model 给模板文件添加数据项
     * @return 返回对应的html模板文件
     */
    @RequestMapping("/bankList")
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
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/finance/bank";
    }

    /**
     * 添加新的推荐银行
     * @param bank 带添加的银行信息
     * @return Msg
     */
    @RequestMapping("/addBank")
    @ResponseBody
    public Msg insertBank(Bank bank) {
        int i = bankService.addBank(bank);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

    /**
     * 获取对应ID的银行信息，方便修改
     * @param id id
     * @return Msg
     */
    @RequestMapping("/getBankInfoById/{id}")
    @ResponseBody
    public Msg getBankInfoById(@PathVariable("id") Integer id){
        Bank bank = bankService.selectBankById(id);
        return Msg.success().add("bank", bank);
    }

    /**
     * 更新推荐银行的信息
     * @param bank 带更新的银行信息
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/updateBank/{update-id}")
    @ResponseBody
    public Msg updateBank(Bank bank, @PathVariable("update-id") Integer id){
        bank.setId(id);
        int i = bankService.updateBank(bank);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

    /**
     * 删除对应ID的推荐银行信息
     * @param id 带删除的推荐银行的ID
     * @return Msg
     */
    @RequestMapping("/deleteBankById/{id}")
    @ResponseBody
    public Msg deleteBankById(@PathVariable("id") Integer id){
        int i =bankService.deleteBankById(id);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
}
