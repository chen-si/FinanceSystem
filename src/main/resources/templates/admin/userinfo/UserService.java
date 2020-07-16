package com.demo.service;

import com.demo.pojo.User;
import com.demo.pojo.UserBankCard;
import com.demo.pojo.UserReputation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public User selectUser(@Param("username") String username);
    public List<User> selectUserAll();

    public void addUser(User user);
    public boolean insertUser(User user);
    public User selectUserById(Integer id);
    public boolean updateUser(User user);
    public boolean deleteUser(Integer id);
    public List<UserBankCard> selectUserBankCardAll();


    public UserBankCard selectGetBankCardById(Integer id);
    public boolean updateBankCard(UserBankCard userBankCard);
    public boolean deleteBankCard(Integer id);


    public List<UserReputation> selectUserReputationAll();
    public boolean updateUserReputation(UserReputation userReputation);

    public boolean updateUserStatus(Integer id);
    public boolean updateUserStatus1(Integer id);
}
