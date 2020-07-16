package com.heu.finance.controller.normal.finance;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.FundProduct;
import com.heu.finance.pojo.finance.UserFundProduct;
import com.heu.finance.service.admin.finance.FundProductService;
import com.heu.finance.service.normal.finance.UserFundProductService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserFundProductController {
    private FundProductService fundProductService;
    private UserFundProductService userFundProductService;

    @Autowired
    public void setUserFundProductService(UserFundProductService userFundProductService) {
        this.userFundProductService = userFundProductService;
    }

    @Autowired
    public void setFundProductService(FundProductService fundProductService) {
        this.fundProductService = fundProductService;
    }

    @RequestMapping("/fundProductList")
    public String selectAllFundProducts(Model model, HttpServletRequest request){

        List<FundProduct> list = fundProductService.selectAllFundProduct();

        model.addAttribute("fundProductList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","fundproductActive");

        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        model.addAttribute("session",session);

        return "user/finance/fundproduct";
    }

    @RequestMapping("/buyFundProduct")
    @ResponseBody
    public Msg buyFundProduct(@RequestParam("fundProductId") Integer fundProductId,
                              @RequestParam("userId") Integer userId,
                              @RequestParam("averYield")BigDecimal averYield){
//        System.out.println(userId);
//        System.out.println(fundProductId);
//        System.out.println(averYield);
        UserFundProduct userFundProduct = new UserFundProduct();
        userFundProduct.setAverYield(averYield);
        userFundProduct.setFundId(fundProductId);
        userFundProduct.setUserId(userId);
        userFundProduct.setProfit(averYield.multiply(new BigDecimal(100)));
        userFundProduct.setStatus(1);

        if (userFundProductService.addUserFundProduct(userFundProduct)  == 1){
            return Msg.success();
        }

        return Msg.failed();
    }
}
