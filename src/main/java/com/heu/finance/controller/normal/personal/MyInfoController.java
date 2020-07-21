package com.heu.finance.controller.normal.personal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.Admin;
import com.heu.finance.pojo.personal.MyInfo;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.normal.personal.MyInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
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
@RequestMapping("/user")
public class MyInfoController {
    private MyInfoService myInfoService;

    @Autowired
    public void setMyInfoService(MyInfoService myInfoService) {
        this.myInfoService = myInfoService;
    }

    @RequestMapping("/myInfo")
    public String myinfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                         Model model, HttpServletRequest request) {
        //引入pagehelper操作
        PageHelper.startPage(pageNum, pageSize);

        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        List<MyInfo> list = myInfoService.selectMyInfo(user.getId());


        //pageinfo封装分页信息
        PageInfo<MyInfo> pageInfo = new PageInfo<MyInfo>(list);
        model.addAttribute("infoPageInfo", pageInfo);
        model.addAttribute("infoList", list);
        model.addAttribute("activeUrl", "indexActive");
//        model.addAttribute("activeUrl1", "personalActive");
//        model.addAttribute("activeUrl2", "recordActive");

        model.addAttribute("session", session);

        return "user/personal/myinfo";
    }

    //已读
    @RequestMapping("/updateInfo/{infoId}")
    @ResponseBody
    public Msg updateInfo(@PathVariable("infoId") Integer infoId){
        int i = myInfoService.updateInfo(infoId);
        if (i==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }

    //删除
    @RequestMapping("/deleteInfo/{infoId}")
    @ResponseBody
    public Msg deleteInfo(@PathVariable("infoId") Integer infoId){
        int j = myInfoService.deleteInfo(infoId);
        if (j==1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
}
