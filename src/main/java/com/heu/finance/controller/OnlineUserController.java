package com.heu.finance.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlineUserController {
    @RequestMapping("/verifyOnline")
    public void verifyOnline(){
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getAttribute("loginUser"));
    }
}
