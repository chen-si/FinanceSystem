package com.heu.finance.controller.normal.tools;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.tools.RecordFlowService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户资金记录页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class RecordFlowController {

    @Autowired
    private RecordFlowService recordFlowService;

    /**
     * 展示用户的资金流动
     * @param pageNum 当前页码 默认为 1
     * @param pageSize 每页显示的数据量，默认为 5
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
    @RequestMapping("/record")
    public String selectChangeMoneyAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       Model model) {
        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
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
