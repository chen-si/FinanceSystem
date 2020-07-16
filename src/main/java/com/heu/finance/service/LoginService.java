package com.heu.finance.service;

import com.heu.finance.pojo.admin.Admin;

public interface LoginService {
    Admin selectUserByUserName(String username);

    void updateAdminStatus(Admin admin);
}
