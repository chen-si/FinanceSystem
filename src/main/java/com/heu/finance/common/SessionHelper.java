package com.heu.finance.common;

import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

public class SessionHelper {
    private static final Map<String, Session> sessionMap = new HashMap<>();

    public static void addSession(String username, Session session){
        sessionMap.put(username, session);
    }

    public static Session getSession(String username){
        return sessionMap.get(username);
    }

    public static void deleteSession(String username){
        Session session = getSession(username);
        if (session != null){
            System.out.println(session);
            sessionMap.remove(username);
            //session.setTimeout(-1L);
            session.removeAttribute("loginUser");
        }
    }
}
