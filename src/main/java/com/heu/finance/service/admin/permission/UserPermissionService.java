package com.heu.finance.service.admin.permission;

import com.heu.finance.pojo.admin.permission.Permission;
import com.heu.finance.pojo.admin.permission.UserPermissions;

import java.util.List;

public interface UserPermissionService {
    List<UserPermissions> getUserPermissionsByUserId(Integer id);
    int addUserPermissions(Integer userId,Integer permissionId);
    int deleteUserPermission(Integer userId,Integer permissionId);
    List<Permission> selectAllUserPermission();
}
