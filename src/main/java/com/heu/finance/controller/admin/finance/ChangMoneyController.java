package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.ChangeMoney;
import com.heu.finance.service.finance.ChangeMoneyService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * 对应 admin端 零钱理财界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class ChangMoneyController {

    @Autowired
    private ChangeMoneyService changeMoneyService;

    /**
     * 展示admin端的零钱理财管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/changeMoneyList")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "orderBy",defaultValue ="default") String orderBy,
                                       Model model) {

        //防止orderBy空设置
        if (orderBy.equals("")){
            orderBy = "default";
        }

        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<ChangeMoney> list = changeMoneyService.selectChangeMoneyOrderBy(orderBy);

        //pageinfo封装分页信息
        PageInfo<ChangeMoney> pageInfo = new PageInfo<ChangeMoney>(list);
        model.addAttribute("financePageInfo", pageInfo);
        model.addAttribute("list", list);
        model.addAttribute("orderBy",orderBy);


        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "changemoneyActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/finance/changemoney";
    }

    /**
     * 新增零钱理财项
     * @param changeMoney 待添加的零钱理财信息
     * @return Msg
     */
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

    /**
     * 获取并返回修改零钱理财回显数据
     * @param id 需要回显的零钱理财项id
     * @return Msg
     */
    @RequestMapping("/getChangeMoneyInfoById/{id}")
    @ResponseBody
    public Msg getChangeMoneyInfoById(@PathVariable("id") Integer id){
        ChangeMoney changeMoney = changeMoneyService.selectChangeMoneyById(id);
        return Msg.success().add("changeMoney",changeMoney);
    }

    /**
     * 更新零钱理财项信息
     * @param id id
     * @param name name
     * @param annualIncome annualIncome
     * @param peiIncome perIncome
     * @param invesMoney invesMoney
     * @param invesTerm invesTerm
     * @return Msg
     */
    @RequestMapping("/updateChangeMoney/{id}")
    @ResponseBody
    public Msg alterChangeMoney(@PathVariable("id") Integer id,
                                @RequestParam("name") String name,
                                @RequestParam("annualIncome") BigDecimal annualIncome,
                                @RequestParam("peiIncome") BigDecimal peiIncome,
                                @RequestParam("invesMoney") BigDecimal invesMoney,
                                @RequestParam("invesTerm") String invesTerm){
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

    /**
     * 删除对应ID的零钱理财项
     * @param id ID
     * @return Msg
     */
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
