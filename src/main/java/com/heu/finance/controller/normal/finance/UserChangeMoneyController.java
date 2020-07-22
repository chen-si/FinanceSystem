package com.heu.finance.controller.normal.finance;


import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.UserChangeMoney;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.service.admin.finance.ChangeMoneyService;
import com.heu.finance.service.normal.finance.UserChangeMoneyService;
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
public class UserChangeMoneyController {
    private UserChangeMoneyService userChangeMoneyService;
    private ChangeMoneyService changeMoneyService;
    private RecordFlowService recordFlowService;

    @Autowired
    public void setUserChangeMoneyService(UserChangeMoneyService userChangeMoneyService) {
        this.userChangeMoneyService = userChangeMoneyService;
    }

    @Autowired
    public void setChangeMoneyService(ChangeMoneyService changeMoneyService) {
        this.changeMoneyService = changeMoneyService;
    }

    @Autowired
    public void setRecordFlowService(RecordFlowService recordFlowService){
        this.recordFlowService = recordFlowService;
    }


    @RequestMapping("/changeMoneyList")
    public ModelAndView selectChangeMoneyAll(Model model){
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "financeActive");
        model.addAttribute("activeUrl2", "changeMoneyActive");
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return new ModelAndView("user/finance/changemoney",
                "changeMoneyList",changeMoneyService.selectChangeMoneyAll());
    }

    //投资
    @RequestMapping("/buyChangeMoney")
    @ResponseBody
    public Msg buyChangeMoney(@RequestParam("changeMoneyId") Integer changeMoneyId,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("invesMoney") BigDecimal invesMoney,
                              @RequestParam("annualIncome") BigDecimal annualIncome,
                              @RequestParam("name") String name){

//        Double averyieid = userChangeMoneyService.selectAverYieIdById(changeMoneyId);
        Date date = new Date();
        UserChangeMoney userChangeMoney = new UserChangeMoney();
        userChangeMoney.setUserId(userId);
        userChangeMoney.setChangeId(changeMoneyId);
        userChangeMoney.setStartTime(date);
//        userChangeMoney.setAverYield(averyieid);
//        userChangeMoney.setProfit(averyieid*100);
        userChangeMoney.setAverYield(annualIncome);
        userChangeMoney.setProfit(annualIncome.multiply(invesMoney));
        userChangeMoney.setStatus(1);

        int i = userChangeMoneyService.addUserChangeMoney(userChangeMoney);

        //将投资记录插入资金流动表中
        RecordFlow recordFlow = new RecordFlow();
        recordFlow.setUserId(userId);
        recordFlow.setFlowMoney(invesMoney);
        recordFlow.setType(1);
        recordFlow.setSource(name);
        recordFlow.setCreateTime(date);
        recordFlow.setFundDesc("无");

        int j = recordFlowService.insertRecord(recordFlow);

        if (i==1 && j ==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
}
