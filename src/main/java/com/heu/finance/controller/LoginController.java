package com.heu.finance.controller;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.admin.Admin;
import com.heu.finance.pojo.admin.userinfo.User;
import com.heu.finance.service.LoginService;
import com.heu.finance.service.admin.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
public class LoginController {
    private LoginService loginService;
    private UserService userService;

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
                           @RequestParam(value = "password") String password,
                           HttpServletRequest request){
        System.out.println(username+"  "+password);
        Admin admin = loginService.selectUserByUserName(username);
        if (admin != null){
            if (admin.getPassword().equals(password)){
                request.getSession().setAttribute("username",username);
                return Msg.success().add("url","/admin/main");
            }else{
                return Msg.failed();
            }
        }else{
            User user = userService.selectUserByUsername(username);
            if (user != null){
                if (user.getPassword().equals(password)){
                    request.getSession().setAttribute("username",username);
                    return Msg.success().add("url","/user/main");
                }
                return Msg.failed();
            }
            return Msg.failed();
        }

    }

}