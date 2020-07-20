package com.heu.finance.controller.normal.personal;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.UserChangeMoney;
import com.heu.finance.pojo.finance.UserFundProduct;
import com.heu.finance.pojo.finance.UserPayMoney;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.normal.finance.UserChangeMoneyService;
import com.heu.finance.service.normal.finance.UserFundProductService;
import com.heu.finance.service.normal.finance.UserPayMoneyService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class MyFinanceController {
    private UserChangeMoneyService userChangeMoneyService;
    private UserPayMoneyService userPayMoneyService;
    private UserFundProductService userFundProductService;

    @Autowired
    public void setUserFundProductService(UserFundProductService userFundProductService) {
        this.userFundProductService = userFundProductService;
    }

    @Autowired
    public void setUserPayMoneyService(UserPayMoneyService userPayMoneyService) {
        this.userPayMoneyService = userPayMoneyService;
    }

    @Autowired
    public void setUserChangeMoneyService(UserChangeMoneyService userChangeMoneyService) {
        this.userChangeMoneyService = userChangeMoneyService;
    }

    @RequestMapping("/myFinance")
    public String showMyFinance(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");
        List<UserChangeMoney> userChangeMoneyList =
                userChangeMoneyService.selectUserChangeMoneyByUserId(user.getId());
//        System.out.println(userChangeMoneyList);

        List<UserPayMoney> userPayMoneyList =
                userPayMoneyService.selectUserPayMoneyByUserId(user.getId());

        List<String> list = new ArrayList<>();

        List<UserFundProduct> userFundProductList =
                userFundProductService.selectUserFundProductByUserId(user.getId());

        model.addAttribute("userChangeMoneyList",userChangeMoneyList);
        model.addAttribute("userPayMoneyList", userPayMoneyList);
        model.addAttribute("userTermFinancialList", list);
        model.addAttribute("userFundProductList", userFundProductList);

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "myFinanceActive");
        //session.setAttribute("myFinanceActiveUrl","changeMoneyActive");

        model.addAttribute("session", session);

        return "user/personal/myfinance";
    }

    @RequestMapping("/revokeUserChangeMoney")
    @ResponseBody
    public Msg revokeUserChangeMoney(@RequestParam("userChangeMoneyId") Integer userChangeMoneyId){
        if (userChangeMoneyService.updateUserChangeMoneyStatus(userChangeMoneyId,3) == 1){
            SecurityUtils.getSubject().getSession()
                    .setAttribute("myFinanceActiveUrl","changeMoneyActive");
            return Msg.success();
        }
        return Msg.failed();
    }

    @RequestMapping("/revokeUserPayMoney")
    @ResponseBody
    public Msg revokeUserPayMoney(@RequestParam("userPayMoneyId") Integer userPayMoneyId){
        if (userPayMoneyService.updateUserPayMoneyStatus(userPayMoneyId,3) == 1){
            SecurityUtils.getSubject().getSession()
                    .setAttribute("myFinanceActiveUrl","payMoneyActive");
            return Msg.success();
        }
        return Msg.failed();
    }

    @RequestMapping("/revokeUserFundProduct")
    @ResponseBody
    public Msg revokeUserFundProduct(@RequestParam("userFundProductId") Integer userFundProductId){
        if (userFundProductService.updateUserFundProductStatus(3,userFundProductId) == 1){
            SecurityUtils.getSubject().getSession()
                    .setAttribute("myFinanceActiveUrl","fundProductActive");
            return Msg.success();
        }
        return Msg.failed();
    }
}
