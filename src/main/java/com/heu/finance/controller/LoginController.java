package com.heu.finance.controller;

import com.heu.finance.common.Msg;

import com.heu.finance.pojo.Admin;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.LoginService;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.admin.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    private LoginService loginService;
    private UserService userService;
//    private OnlineUserService onlineUserService;
//
//    @Autowired
//    public void setOnlineUserService(OnlineUserService onlineUserService) {
//        this.onlineUserService = onlineUserService;
//    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginVerifyUsername/{username}")
    @ResponseBody
    public Msg loginVerifyUsername(@PathVariable("username") String username){
        Admin admin = loginService.selectUserByUserName(username);
        if(admin != null){
            return Msg.success();
        }else{
            User user = userService.selectUserByUsername(username);
            if (user != null){
                return Msg.success();
            }
        }
        return Msg.failed();
    }

    @RequestMapping("/verifyLogin")
    @ResponseBody
    public Msg verifyLogin(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password){
        //System.out.println(username+"   "+password);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token =  new UsernamePasswordToken(username,password);

        subject.login(token);

        if(subject.hasRole("admin")){
            return Msg.success().add("url","/admin/main");
        }else if( subject.hasRole("user")){
            return  Msg.success().add("url","/user/main");
        }else{
            return Msg.failed();
        }
    }

}
