package com.heu.finance.mapper;

import com.heu.finance.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    Admin selectUserByUserName(String username);
    void updateAdminStatus(Admin admin);
}
