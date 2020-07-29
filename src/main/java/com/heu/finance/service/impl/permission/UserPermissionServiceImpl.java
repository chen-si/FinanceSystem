package com.heu.finance.service.impl.permission;

import com.heu.finance.mapper.permission.UserPermissionMapper;
import com.heu.finance.pojo.permission.Permission;
import com.heu.finance.pojo.permission.UserPermissions;
import com.heu.finance.service.permission.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    private UserPermissionMapper userPermissionMapper;

    @Autowired
    public void setUserPermissionMapper(UserPermissionMapper userPermissionMapper) {
        this.userPermissionMapper = userPermissionMapper;
    }


    @Override
    public List<UserPermissions> getUserPermissionsByUserId(Integer id) {
        return userPermissionMapper.getUserPermissionsByUserId(id);
    }

    @Override
    public int addUserPermissions(Integer userId,Integer permissionId) {
        return userPermissionMapper.addUserPermissions(userId,permissionId);
    }

    @Override
    public int deleteUserPermission(Integer userId,Integer permissionId) {
        return userPermissionMapper.deleteUserPermission(userId,permissionId);
    }

    @Override
    public List<Permission> selectAllUserPermission() {
        return userPermissionMapper.selectAllUserPermission();
    }
}
