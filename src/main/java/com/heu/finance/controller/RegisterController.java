package com.heu.finance.controller;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册界面
 * @version 1.0
 * @author Liu,Qin,Zhou
 */
@Controller
@RequestMapping("/Register")
public class RegisterController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 去注册界面
     * @return 注册界面模板文件路径
     */
    @RequestMapping("/")
    public String Register(){
        return "register";
    }

    /**
     * 用户注册功能
     * @param username username
     * @param password password
     * @return Msg
     */
    @RequestMapping("/userRegister")
    @ResponseBody
    public Msg registerUser(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userService.newUser(user);

        if (user.getId() != null){
            return Msg.success().add("url","/");
        }

        return Msg.failed();
    }
}
