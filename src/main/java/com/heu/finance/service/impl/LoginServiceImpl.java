package com.heu.finance.service.impl;

import com.heu.finance.mapper.admin.AdminMapper;
import com.heu.finance.pojo.admin.Admin;
import com.heu.finance.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private AdminMapper adminMapper;

    @Autowired
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin selectUserByUserName(String username) {
        return adminMapper.selectUserByUserName(username);
    }
}
