package com.heu.finance.service;

import com.heu.finance.pojo.Admin;

public interface LoginService {
    Admin selectUserByUserName(String username);

    void updateAdminStatus(Admin admin);
}
