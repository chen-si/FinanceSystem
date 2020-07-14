package com.heu.finance.service.admin.userinfo;

import com.heu.finance.pojo.admin.userinfo.User;

import java.util.List;

public interface UserService {
    List<User> selectAllUser();

    void insertUser(User user);

    User getUserById(Integer id);

    int updateUserInfos(User user);

    int deleteUserById(Integer id);

    User selectUserByUsername(String username);

    void newUser(User user);
}
