package com.heu.finance.controller;

import com.heu.finance.common.Msg;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.admin.userinfo.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class OnlineUserController {
    private static OnlineUserService onlineUserService;
    private static UserService userService;
    private static Map<User, Date> OnlineUsers;
    private static boolean flag = true;

    @Autowired
    public void setOnlineUserService(OnlineUserService onlineUserService) {
        OnlineUserController.onlineUserService = onlineUserService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        OnlineUserController.userService = userService;
    }

    @RequestMapping("/verifyOnline")
    @ResponseBody
    public Msg verifyOnline(){
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("loginUser");

        System.out.println(user);

        if(OnlineUsers == null){
            OnlineUsers = new HashMap<>();
        }
        OnlineUsers.put(user,new Date());
        System.out.println(OnlineUsers);

        if (flag){
            deleteOfflineUser();
            flag = false;
        }

        return Msg.success();
    }

    public static void deleteOfflineUser(){
        if (OnlineUsers == null){
            OnlineUsers = new HashMap<>();
        }
        Runnable task = () ->{
            System.out.println("Concurrency on");
            try {
                do {
                    Thread.sleep(1000);
                    for (Map.Entry<User, Date> userDateEntry : OnlineUsers.entrySet()) {
                        User user = (User) ((Map.Entry) userDateEntry).getKey();
                        Date lastVerifyTime = (Date) ((Map.Entry) userDateEntry).getValue();
                        long delay = new Date().getTime() - lastVerifyTime.getTime();
                        if (delay > 1000 * 2) {
                            //超过两分钟，用户登录状态自动失效
                            OnlineUsers.remove(user);

                            user.setStatus(0);
                            userService.updateUserStatus(user);

                            onlineUserService.userLogout(user.getUsername());
                        }
                    }
                } while (OnlineUsers != null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}