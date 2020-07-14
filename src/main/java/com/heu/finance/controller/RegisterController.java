package com.heu.finance.controller;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.admin.userinfo.User;
import com.heu.finance.service.admin.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Register")
public class RegisterController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String Register(){
        return "register";
    }

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
