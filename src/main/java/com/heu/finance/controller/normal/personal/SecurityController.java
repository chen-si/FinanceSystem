package com.heu.finance.controller.normal.personal;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户账户安全页面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/user")
public class SecurityController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 展示用户帐号按钱页面
     * @param model 给模板文件添加数据项
     * @return 模板文件对应位置
     */
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

    /**
     * 更新帐号密码
     * @param id id
     * @param oldPwd oldPwd
     * @param newPwd newPWd
     * @return Msg
     */
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
