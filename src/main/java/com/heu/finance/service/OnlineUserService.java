package com.heu.finance.service;

import com.heu.finance.common.RedisConfig;

public interface OnlineUserService {
    void addUser(String username);

    void userLogout(String username);

    boolean isLogin(String username);
}
