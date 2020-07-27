package com.heu.finance.controller.normal.finance;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.UserPayMoney;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.service.admin.finance.PayMoneyService;
import com.heu.finance.service.normal.finance.UserPayMoneyService;
import com.heu.finance.service.normal.tools.RecordFlowService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserPayMoneyController {
    private UserPayMoneyService userPayMoneyService;
    private PayMoneyService payMoneyService;
    private RecordFlowService recordFlowService;

    @Autowired
    public void setRecordFlowService(RecordFlowService recordFlowService) {
        this.recordFlowService = recordFlowService;
    }

    @Autowired
    public void setUserPayMoneyService(UserPayMoneyService userPayMoneyService) {
        this.userPayMoneyService = userPayMoneyService;
    }

    @Autowired
    public void setPayMoneyService(PayMoneyService payMoneyService) {
        this.payMoneyService = payMoneyService;
    }

    @RequestMapping("/payMoneyList")
    public ModelAndView selectChangeMoneyAll(@RequestParam(value = "orderBy",defaultValue = "default") String orderBy,Model model){
        //防止orderBy为空设置
        if (orderBy.equals("")){
            orderBy = "default";
        }

        model.addAttribute("orderBy",orderBy);

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "payMoneyActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return new ModelAndView("user/finance/paymoney","payMoneyList",
                payMoneyService.selectPayMoneyOrderBy(orderBy));
    }

    //投资
    @RequestMapping("/buyPayMoney")
    @ResponseBody
    public Msg buyChangeMoney(@RequestParam("payMoneyId") Integer payMoneyId,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("monthMoney") BigDecimal monthMoney,
                              @RequestParam("name") String name){
//        System.out.println(monthMoney);
        Date date = new Date();
        UserPayMoney userPayMoney =new UserPayMoney();
        userPayMoney.setUserId(userId);
        userPayMoney.setPayId(payMoneyId);
        userPayMoney.setStartTime(date);
        userPayMoney.setAverYield(new BigDecimal("0.03123"));
        userPayMoney.setProfit(monthMoney.multiply(userPayMoney.getAverYield()));
        userPayMoney.setStatus(1);

        //将投资记录插入资金流动表中
        RecordFlow recordFlow = new RecordFlow();
        recordFlow.setUserId(userId);
        recordFlow.setFlowMoney(monthMoney);
        recordFlow.setType(1);
        recordFlow.setSource("工资理财");
        recordFlow.setCreateTime(date);
        recordFlow.setFundDesc(name);

        int j = recordFlowService.insertRecord(recordFlow);

        int i = userPayMoneyService.addUserPayMoney(userPayMoney);
        if (i == 1 && j == 1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

}
