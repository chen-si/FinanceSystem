package com.heu.finance.mapper.admin.userinfo;

import com.heu.finance.pojo.admin.userinfo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> selectAllUser();

    void insertUser(User user);

    void newUser(User user);

    User getUserById(Integer id);

    int updateUserInfos(User user);

    int deleteUserById(Integer id);

    User selectUserByUsername(String username);
}
