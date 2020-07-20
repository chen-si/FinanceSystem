package com.heu.finance.controller.normal.personal;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.admin.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class SecurityController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/security")
    public String toSecurityPage(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        model.addAttribute("activeUrl", "indexActive");
        model.addAttribute("activeUrl1", "personalActive");
        model.addAttribute("activeUrl2", "securityActive");

        model.addAttribute("session",session);
        return "user/personal/security";
    }

    @RequestMapping("/updatePwd")
    @ResponseBody
    public Msg updatePwd(@RequestParam("id") Integer id,
                         @RequestParam("oldpwd") String oldPwd,
                         @RequestParam("newpwd") String newPwd){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        if(user.getPassword().equals(oldPwd)){
            user.setPassword(newPwd);

            if (userService.updateUserPwd(user) == 1){
                session.setAttribute("loginUser",user);
                return Msg.success();
            }
        }

        return Msg.failed();
    }
}
