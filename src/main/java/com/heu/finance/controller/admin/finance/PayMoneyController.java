package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;

import com.heu.finance.pojo.finance.PayMoney;
import com.heu.finance.service.finance.PayMoneyService;
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
 * 对应 admin端 工资理财界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class PayMoneyController {

    @Autowired
    private PayMoneyService payMoneyService;

    /**
     * 展示admin端的基金理财管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param orderBy 查询字段，默认为'default'
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/payMoneyList")
    public String selectPayMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                    @RequestParam(value = "orderBy",defaultValue = "default") String orderBy,
                                    Model model) {

        //防止orderBy空设置
        if (orderBy.equals("")){
            orderBy = "default";
        }

        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        List<PayMoney> list1 = payMoneyService.selectPayMoneyOrderBy(orderBy);

        //pageinfo封装分页信息
        PageInfo<PayMoney> pageInfo = new PageInfo<PayMoney>(list1);
        model.addAttribute("financePageInfo", pageInfo);
        model.addAttribute("list", list1);

        model.addAttribute("orderBy",orderBy);

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "paymoneyActive");
        model.addAttribute("session", SecurityUtils.getSubject().getSession());
        return "admin/finance/paymoney";

    }

    /**
     * 新增工资理财项
     * @param payMoney 新增项
     * @return Msg
     */
    @RequestMapping("/addPayMoney")
    @ResponseBody
    public Msg insertPayMoney(PayMoney payMoney) {
        int i = payMoneyService.addPayMoney(payMoney);
        if (i == 1) {
            return Msg.success();
        } else {
            return Msg.failed();
        }
    }

    /**
     * 获取并返回修改工资理财回显数据
     * @param id 需要回显的工资理财项id
     * @return Msg
     */
    @RequestMapping("/getPayMoneyInfoById/{id}")
    @ResponseBody
    public Msg getPayMoneyInfoById(@PathVariable("id") Integer id) {
        PayMoney payMoney = payMoneyService.selectPayMoneyById(id);
        return Msg.success().add("payMoney", payMoney);
    }

    /**
     * 更新/修改 工资理财项的信息
     * @param id id
     * @param type type
     * @param monthMoney monthMoney
     * @param autoInto autoInto
     * @param invesTerm invvvesTerm
     * @return Msg
     */
    @RequestMapping("/updatePayMoney/{id}")
    @ResponseBody
    public Msg updatePayMoney(@PathVariable("id") Integer id,
                              @RequestParam("type") Integer type,
                              @RequestParam("monthMoney") BigDecimal monthMoney,
                              @RequestParam("autoInto") Integer autoInto,
                              @RequestParam("invesTerm") String invesTerm) {
        PayMoney payMoney = new PayMoney();
        payMoney.setId(id);
        payMoney.setType(type);
        payMoney.setMonthMoney(monthMoney);
        payMoney.setAutoInto(autoInto);
        payMoney.setInvesTerm(invesTerm);
        int j = payMoneyService.updatePayMoney(payMoney);
        if (j == 1) {
            return Msg.success();
        } else {
            return Msg.failed();
        }
    }

    /**
     * 删除对应ID的工资理财项
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/deletePayMoneyById/{id}")
    @ResponseBody
    public Msg deletePayMoneyById(@PathVariable("id") Integer id) {
        int k = payMoneyService.deletePayMoney(id);
        if (k == 1) {
            return Msg.success();
        } else {
            return Msg.failed();
        }
    }
}
