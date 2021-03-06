package com.heu.finance.mapper.permission;

import com.heu.finance.pojo.permission.Permission;
import com.heu.finance.pojo.permission.UserPermissions;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserPermissionMapper {
    List<UserPermissions> getUserPermissionsByUserId(Integer id);
    int addUserPermissions(Integer userId,Integer permissionId);
    int deleteUserPermission(Integer userId,Integer permissionId);
    List<Permission> selectAllUserPermission();
}
