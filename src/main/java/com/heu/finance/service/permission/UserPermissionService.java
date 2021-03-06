package com.heu.finance.service.permission;

import com.heu.finance.pojo.permission.Permission;
import com.heu.finance.pojo.permission.UserPermissions;

import java.util.List;

public interface UserPermissionService {
    List<UserPermissions> getUserPermissionsByUserId(Integer id);
    int addUserPermissions(Integer userId,Integer permissionId);
    int deleteUserPermission(Integer userId,Integer permissionId);
    List<Permission> selectAllUserPermission();
}
