package com.heu.finance.controller.admin.userinfo;

import com.demo.common.Msg;
import com.demo.pojo.User;
import com.demo.service.UserReputationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserReputationController {
    @Autowired
    private UserReputationService userReputationService;

    @RequestMapping("/admin/reputation")
    public String selectUserAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                Model model) {
        //引入pagehelper插件
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userReputationService.selectUserReputationAll();
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

    @RequestMapping("/user/getUserReputationById/{id}")
    @ResponseBody
    public Msg getUserInfoById(@PathVariable("id") Integer id){
        User user = userReputationService.selectUserReputationById(id);
        return Msg.success().add("user", user);
    }

    @RequestMapping("/user/updateUserReputation/{id}")
    @ResponseBody
    public Msg updateUserProfile(User user, @PathVariable("id") Integer id){
        user.setId(id);
        int i = userReputationService.updateUserProfile(user);
        if(i==1){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
