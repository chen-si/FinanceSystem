package com.heu.finance.mapper.admin;

import com.heu.finance.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    Admin selectUserByUserName(String username);
    void updateAdminStatus(Admin admin);
}
