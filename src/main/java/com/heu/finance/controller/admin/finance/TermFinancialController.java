package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.TermFinancial;
import com.heu.finance.service.admin.finance.TermFinancialService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TermFinancialController {
    private TermFinancialService termFinancialService;

    @Autowired
    public void setTermFinancialService(TermFinancialService termFinancialService) {
        this.termFinancialService = termFinancialService;
    }

    @RequestMapping("/termFinancialList")
    public String selectAllFundProduct(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                       Model model, HttpServletRequest request){
        PageHelper.startPage(pageNum,pageSize);

        List<TermFinancial> list = termFinancialService.selectAllTermFinancial();
        while(list.isEmpty() && !pageNum.equals(1)){
            PageHelper.startPage(pageNum - 1,pageSize);
            list = termFinancialService.selectAllTermFinancial();
        }

        PageInfo<TermFinancial> pageInfo = new PageInfo<>(list);


        model.addAttribute("termFinancialPageInfo",pageInfo);
        model.addAttribute("termFinancialList",list);

        model.addAttribute("activeUrl","indexActive");
        model.addAttribute("activeUrl1","financeActive");
        model.addAttribute("activeUrl2","termfinancialActive");
        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "admin/finance/termfinancial";
    }

    @RequestMapping("/addTermFinancial")
    @ResponseBody
    public Msg addTermFinancial(TermFinancial termFinancial){
        //System.out.println(termFinancial);
        termFinancialService.insertTermFinancial(termFinancial);
        if (termFinancial.getId() != null){
            return Msg.success();
        }
        return Msg.failed();
    }

    @RequestMapping("/getTermFinancialById/{id}")
    @ResponseBody
    public Msg getTermFinancial(@PathVariable("id") Integer id){
        TermFinancial termFinancial = termFinancialService.getTermFinancialById(id);
        if (termFinancial != null){
            return Msg.success().add("termFinancial",termFinancial);
        }
        return  Msg.failed();
    }

    @RequestMapping("/updateTermFinancial/{id}")
    @ResponseBody
    public Msg updateTermFinancial(@PathVariable("id") Integer id,
                                   TermFinancial termFinancial){
        termFinancial.setId(id);
        if (termFinancialService.updateTermFinancialInfos(termFinancial) == 1){
            return Msg.success();
        }
        return Msg.failed();
    }

    @RequestMapping("/deleteTermFinancialById/{id}")
    @ResponseBody
    public Msg deleteTermFinancial(@PathVariable("id") Integer id){
        if(termFinancialService.deleteTermFinancialById(id) == 1){
            return Msg.success();
        }else{
            return Msg.failed();
        }
    }
}
