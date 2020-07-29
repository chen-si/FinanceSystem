package com.heu.finance.service.permission;

import com.heu.finance.pojo.permission.AdminPermissions;
import com.heu.finance.pojo.permission.Permission;

import java.util.List;

public interface AdminPermissionService {
    List<AdminPermissions> getAdminPermissionsByAdminId(Integer id);
    int addAdminPermissions(Integer adminId,Integer permissionId);
    int deleteAdminPermissions(Integer adminId,Integer permissionId);
    List<Permission> selectAllAdminPermission();
}
