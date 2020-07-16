package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.FundProduct;
import com.heu.finance.service.admin.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class FundProductController {
    private FundProductService fundProductService;
    @Autowired
    public void setFundProductService(FundProductService fundProductService) {
        this.fundProductService = fundProductService;
    }

    @RequestMapping("/fundProductList")
    public String selectAllFundProduct(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                       Model model, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);

        List<FundProduct> list = fundProductService.selectAllFundProduct();
        while(list.isEmpty() && !pageNum.equals(1)){
            PageHelper.startPage(pageNum - 1,pageSize);
            list = fundProductService.selectAllFundProduct();
        }

        PageInfo<FundProduct> pageInfo = new PageInfo<>(list);


        model.addAttribute("fundProductPageInfo",pageInfo);
        model.addAttribute("fundProductList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","fundproductActive");
        model.addAttribute("username",request.getSession().getAttribute("username"));

        return "admin/finance/fundproduct";
    }

    @RequestMapping("/addFundProduct")
    @ResponseBody
    public Msg addFundProduct(@RequestParam(value = "code") Integer code,
                              @RequestParam(value = "type") Integer type,
                              @RequestParam(value = "funddesc") String fundDesc,
                              @RequestParam(value = "dailygrowth") BigDecimal dailyGrowth,
                              @RequestParam(value = "monthlygrowth") BigDecimal monthlyGrowth,
                              @RequestParam(value = "annualgrowth") BigDecimal annualGrowth,
                              @RequestParam(value = "leastmoney") BigDecimal leastMoney,
                              @RequestParam(value = "investerm") String invesTerm){
        FundProduct fundProduct = new FundProduct();
        fundProduct.setAnnualGrowth(annualGrowth);
        fundProduct.setCode(code);
        fundProduct.setDailyGrowth(dailyGrowth);
        fundProduct.setInvesTerm(invesTerm);
        fundProduct.setType(type);
        fundProduct.setMonthlyGrowth(monthlyGrowth);
        fundProduct.setLeastMoney(leastMoney);
        fundProduct.setFundDesc(fundDesc);
        System.out.println(fundProduct);

        fundProductService.insertFundProduct(fundProduct);
        if (fundProduct.getId() != null){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
    @RequestMapping("/getFundProductInfoById/{id}")
    @ResponseBody
    public Msg getFundProductById(@PathVariable("id") Integer id){
        FundProduct fundProduct = fundProductService.getFundProductById(id);
        if (fundProduct != null){
            return Msg.success().add("fundProduct",fundProduct);
        }else{
            return Msg.failed();
        }
    }

    @RequestMapping("/updateFundProduct/{id}")
    @ResponseBody
    public Msg updateFundProduct(@PathVariable("id") Integer id,
                                 @RequestParam(value = "code") Integer code,
                                 @RequestParam(value = "type") Integer type,
                                 @RequestParam(value = "funddesc") String fundDesc,
                                 @RequestParam(value = "dailygrowth") BigDecimal dailyGrowth,
                                 @RequestParam(value = "monthlygrowth") BigDecimal monthlyGrowth,
                                 @RequestParam(value = "annualgrowth") BigDecimal annualGrowth,
                                 @RequestParam(value = "leastmoney") BigDecimal leastMoney,
                                 @RequestParam(value = "investerm") String invesTerm){
        FundProduct fundProduct = new FundProduct();
        fundProduct.setId(id);
        fundProduct.setAnnualGrowth(annualGrowth);
        fundProduct.setCode(code);
        fundProduct.setDailyGrowth(dailyGrowth);
        fundProduct.setInvesTerm(invesTerm);
        fundProduct.setType(type);
        fundProduct.setMonthlyGrowth(monthlyGrowth);
        fundProduct.setLeastMoney(leastMoney);
        fundProduct.setFundDesc(fundDesc);

        if (fundProductService.updateFundProductInfos(fundProduct) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }

    @RequestMapping("/deleteFundProductById/{id}")
    @ResponseBody
    public Msg deleteFundProductById(@PathVariable("id") Integer id){
        if (fundProductService.deleteFundProductById(id) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }
}
