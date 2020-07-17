package com.heu.finance.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class LogoutController {
    @RequestMapping("/logout")
    public String logout(){
        return "login";
    }
}
