package com.heu.finance.controller.normal.finance;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.TermFinancial;
import com.heu.finance.pojo.finance.UserTermFinancial;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.finance.TermFinancialService;
import com.heu.finance.service.finance.UserTermFinancialService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户期限理财页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class UserTermFinancialController {
    private TermFinancialService termFinancialService;
    private UserTermFinancialService userTermFinancialService;

    @Autowired
    public void setUserTermFinancialService(UserTermFinancialService userTermFinancialService) {
        this.userTermFinancialService = userTermFinancialService;
    }

    @Autowired
    public void setTermFinancialService(TermFinancialService termFinancialService) {
        this.termFinancialService = termFinancialService;
    }

    /**
     * 展示用户期限理财界面
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/termFinancial")
    public String termFinancial(Model model){
        List<TermFinancial> list = termFinancialService.selectAllTermFinancial();

        model.addAttribute("termFinancialList",list);
        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","termFinancialActive");

        model.addAttribute("session",SecurityUtils.getSubject().getSession());

        return  "/user/finance/termfinancial";
    }

    /**
     * 投资期限理财
     * @param termFinancialId termFinancialId
     * @param leastMoney  leastMoney
     * @param annualIncome  annualIncome
     * @return Msg
     */
    @RequestMapping("/buyTermFinancial")
    @ResponseBody
    public Msg buyTermFinancial(@RequestParam("termFinancialId") Integer termFinancialId,
                                @RequestParam("leastMoney") BigDecimal leastMoney,
                                @RequestParam("annualIncome") BigDecimal annualIncome){
        UserTermFinancial userTermFinancial = new UserTermFinancial();

        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        userTermFinancial.setUserId(user.getId());
        userTermFinancial.setTermId(termFinancialId);
        userTermFinancial.setStartTime(new Date());

        userTermFinancial.setAverYield(annualIncome);//这里写死了，以后再优化
        userTermFinancial.setProfit(annualIncome.multiply(leastMoney));
        userTermFinancial.setStatus(1);

        if(userTermFinancialService.insertUserTermFinancial(userTermFinancial)){
            return Msg.success();
        }
        return Msg.failed();
    }
}
