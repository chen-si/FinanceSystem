package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.admin.finance.ChangeMoney;
import com.heu.finance.service.admin.finance.ChangeMoneyService;
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
public class ChangMoneyController {

    @Autowired
    private ChangeMoneyService changeMoneyService;

    @RequestMapping("/changeMoneyList")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       Model model) {
        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<ChangeMoney> list = changeMoneyService.selectChangeMoneyAll();

        //pageinfo封装分页信息
        PageInfo<ChangeMoney> pageInfo = new PageInfo<ChangeMoney>(list);
        model.addAttribute("financePageInfo", pageInfo);
        model.addAttribute("list", list);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "changemoneyActive");

        model.addAttribute("username", "username");
        return "admin/finance/changemoney";


    }
    //新增
    @RequestMapping("/addChangeMoney")
    @ResponseBody
    public Msg insertChangeMoney(ChangeMoney changeMoney){
        int i = changeMoneyService.addChangeMoney(changeMoney);
        if (i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
    //修改回显
    @RequestMapping("/getChangeMoneyInfoById/{id}")
    @ResponseBody
    public Msg getChangeMoneyInfoById(@PathVariable("id") Integer id){
        ChangeMoney changeMoney = changeMoneyService.selectChangeMoneyById(id);
        return Msg.success().add("changeMoney",changeMoney);
    }

    //修改
    @RequestMapping("/updateChangeMoney/{id}")
    @ResponseBody
    public Msg alterChangeMoney(@PathVariable("id") Integer id,@RequestParam("name") String name,@RequestParam("annualIncome") Double annualIncome,@RequestParam("peiIncome") Double peiIncome,@RequestParam("invesMoney") Double invesMoney,@RequestParam("invesTerm") String invesTerm){
        ChangeMoney changeMoney = new ChangeMoney();
        changeMoney.setId(id);
        changeMoney.setName(name);
        changeMoney.setAnnualIncome(annualIncome);
        changeMoney.setPeiIncome(peiIncome);
        changeMoney.setInvesMoney(invesMoney);
        changeMoney.setInvesTerm(invesTerm);
        int j = changeMoneyService.alterChangeMoney(changeMoney);
        if (j==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

    //删除
    @RequestMapping("/deleteChangeMoneyById/{id}")
    @ResponseBody
    public Msg deleteChangeMoneyById(@PathVariable("id") Integer id){
        int k = changeMoneyService.deleteChangeMoney(id);
        if(k==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }

    }


}
