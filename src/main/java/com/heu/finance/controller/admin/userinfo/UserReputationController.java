package com.heu.finance.controller.admin.userinfo;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.admin.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserReputationController {
    @Autowired
    private UserService userService;

    @RequestMapping("/reputationList")
    public String selectUserAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                Model model) {
        //引入pagehelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.selectUserReputationAll();
        //PageInfo封装分页信息
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        model.addAttribute("userPageInfo", pageInfo);
        model.addAttribute("userList", userList);
        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "userInfoActive");
        model.addAttribute("activeUrl2", "reputationActive");
        model.addAttribute("username", "username");

        return "admin/userinfo/reputation";
    }

    @RequestMapping("/getUserReputationById/{id}")
    @ResponseBody
    public Msg getUserInfoById(@PathVariable("id") Integer id){
        User user = userService.selectUserReputationById(id);
        if( user != null){
            return Msg.success().add("user", user);
        }
        return Msg.failed();
    }

    @RequestMapping("/updateUserReputation/{id}")
    @ResponseBody
    public Msg updateUserProfile(User user, @PathVariable("id") Integer id){
        user.setId(id);
        int i = userService.updateUserProfile(user);
        if(i == 1){
            return Msg.success();
        }else {
            return Msg.failed();
        }
    }
}
