package com.heu.finance.controller.admin.finance;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.finance.TermFinancial;
import com.heu.finance.service.finance.TermFinancialService;
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

/**
 * 对应 admin端 期限理财界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/admin")
public class TermFinancialController {
    private TermFinancialService termFinancialService;

    @Autowired
    public void setTermFinancialService(TermFinancialService termFinancialService) {
        this.termFinancialService = termFinancialService;
    }

    /**
     * 展示admin端的期限理财管理页面
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
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

    /**
     * 新增期限理财项
     * @param termFinancial 新增项
     * @return Msg
     */
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

    /**
     * 获取并返回修改期限理财回显数据
     * @param id 需要回显的期限理财项id
     * @return Msg
     */
    @RequestMapping("/getTermFinancialById/{id}")
    @ResponseBody
    public Msg getTermFinancial(@PathVariable("id") Integer id){
        TermFinancial termFinancial = termFinancialService.getTermFinancialById(id);
        if (termFinancial != null){
            return Msg.success().add("termFinancial",termFinancial);
        }
        return  Msg.failed();
    }

    /**
     * 更新修改ID对应的期限理财项
     * @param id ID
     * @param termFinancial 待更新数据
     * @return Msg
     */
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

    /**
     * 删除对应ID的期限理财项
     * @param id ID
     * @return Msg
     */
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
