package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.FundProduct;
import com.heu.finance.service.finance.FundProductService;
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
 * 对应 admin端 基金理财界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class FundProductController {
    private FundProductService fundProductService;
    @Autowired
    public void setFundProductService(FundProductService fundProductService) {
        this.fundProductService = fundProductService;
    }

    /**
     * 展示admin端的基金理财管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/fundProductList")
    public String selectAllFundProduct(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                       Model model){
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
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/finance/fundproduct";
    }

    /**
     * 添加基金理财项
     * @param code code
     * @param type type
     * @param fundDesc fundDesc
     * @param dailyGrowth dailyGrowth
     * @param monthlyGrowth monthlyGrowth
     * @param annualGrowth annualGrowth
     * @param leastMoney leastMoney
     * @param invesTerm invesTerm
     * @return Msg
     */
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

    /**
     * 获取并返回修改基金理财回显数据
     * @param id 需要回显的基金理财项id
     * @return Msg
     */
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

    /**
     * 更新/修改 基金理财项
     * @param code code
     * @param type type
     * @param fundDesc fundDesc
     * @param dailyGrowth dailyGrowth
     * @param monthlyGrowth monthlyGrowth
     * @param annualGrowth annualGrowth
     * @param leastMoney leastMoney
     * @param invesTerm invesTerm
     * @return Msg
     */
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

    /**
     * 删除对应ID的基金理财项
     * @param id ID
     * @return Msg
     */
    @RequestMapping("/deleteFundProductById/{id}")
    @ResponseBody
    public Msg deleteFundProductById(@PathVariable("id") Integer id){
        if (fundProductService.deleteFundProductById(id) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }
}
