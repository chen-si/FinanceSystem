package com.heu.finance.service.impl.admin.permission;

import com.heu.finance.mapper.admin.permission.AdminPermissionMapper;
import com.heu.finance.pojo.permission.AdminPermissions;
import com.heu.finance.pojo.permission.Permission;
import com.heu.finance.service.admin.permission.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {
    private AdminPermissionMapper adminPermissionMapper;

    @Autowired
    public void setAdminPermissionMapper(AdminPermissionMapper adminPermissionMapper) {
        this.adminPermissionMapper = adminPermissionMapper;
    }

    @Override
    public List<AdminPermissions> getAdminPermissionsByAdminId(Integer id) {
        return adminPermissionMapper.getAdminPermissionsByAdminId(id);
    }

    @Override
    public int addAdminPermissions(Integer adminId,Integer permissionId) {
        return adminPermissionMapper.addAdminPermissions(adminId,permissionId);
    }

    @Override
    public int deleteAdminPermissions(Integer adminId, Integer permissionId) {
        return adminPermissionMapper.deleteAdminPermissions(adminId,permissionId);
    }


    @Override
    public List<Permission> selectAllAdminPermission() {
        return adminPermissionMapper.selectAllAdminPermission();
    }
}
