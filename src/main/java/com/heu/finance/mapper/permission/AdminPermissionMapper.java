package com.heu.finance.mapper.permission;

import com.heu.finance.pojo.permission.AdminPermissions;
import com.heu.finance.pojo.permission.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminPermissionMapper {
    List<AdminPermissions> getAdminPermissionsByAdminId(Integer id);
    int addAdminPermissions(Integer adminId,Integer permissionId);
    int deleteAdminPermissions(Integer adminId,Integer permissionId);
    List<Permission> selectAllAdminPermission();
}
