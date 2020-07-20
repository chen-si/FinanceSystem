package com.heu.finance.controller.normal.tools;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.pojo.Admin;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.normal.tools.RecordFlowService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class RecordFlowController {

    @Autowired
    private RecordFlowService recordFlowService;


    @RequestMapping("/record")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       Model model, HttpServletRequest request) {
        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);
        User user = (User) request.getSession().getAttribute("loginUser");
        List<RecordFlow> list = recordFlowService.selectRecord(user.getId());


        //pageinfo封装分页信息
        PageInfo<RecordFlow> pageInfo = new PageInfo<RecordFlow>(list);
        model.addAttribute("flowOfFundsPageInfo", pageInfo);
        model.addAttribute("flowOfFundsList", list);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "toolsActive");
        model.addAttribute("activeUrl2", "recordActive");

        model.addAttribute("session", SecurityUtils.getSubject().getSession());

        return "user/tools/record";
    }
}
