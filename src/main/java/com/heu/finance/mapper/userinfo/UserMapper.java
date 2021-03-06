package com.heu.finance.mapper.userinfo;

import com.heu.finance.pojo.userinfo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> selectAllUserOrderBy(String orderBy);

    List<User> selectAllUser();

    void insertUser(User user);

    void newUser(User user);

    User getUserById(Integer id);

    int updateUserInfos(User user);

    int updateUserPwd(User user);

    int deleteUserById(Integer id);

    User selectUserByUsername(String username);

    int updateUserStatus(User user);

    List<User> selectUserReputationAll();

    User selectUserReputationById(Integer id);

    int updateUserProfile(User user);

}
