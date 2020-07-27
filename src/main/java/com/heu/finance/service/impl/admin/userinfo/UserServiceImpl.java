package com.heu.finance.service.impl.admin.userinfo;

import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.admin.userinfo.UserMapper;
import com.heu.finance.pojo.userinfo.User;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.admin.userinfo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAllUserOrderBy(String orderBy) {
        return userMapper.selectAllUserOrderBy(orderBy);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        User user = JSON.parseObject(redisService.hashGet(RedisConfig.UserInfoKey,id.toString()),User.class);
        if(user == null){
            user = userMapper.getUserById(id);
            redisService.hashSet(RedisConfig.UserInfoKey,id.toString(),JSON.toJSON(user).toString());
        }
        return user;
    }

    @Override
    public int updateUserInfos(User user) {
        redisService.hashRemove(RedisConfig.UserInfoKey,user.getId().toString());
        return userMapper.updateUserInfos(user);
    }

    @Override
    public int updateUserPwd(User user) {
        redisService.hashRemove(RedisConfig.UserInfoKey,user.getId().toString());
        return userMapper.updateUserPwd(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        redisService.hashRemove(RedisConfig.UserInfoKey,id.toString());
        return userMapper.deleteUserById(id);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public void newUser(User user) {
        userMapper.newUser(user);
    }

    @Override
    public int updateUserStatus(User user) {
        redisService.hashRemove(RedisConfig.UserInfoKey,user.getId().toString());
        return userMapper.updateUserStatus(user);
    }

    @Override
    public List<User> selectUserReputationAll (){
        return userMapper.selectUserReputationAll();
    }

    @Override
    public User selectUserReputationById(Integer id) {
        return userMapper.selectUserReputationById(id);
    }

    @Override
    public int updateUserProfile(User user) {
        redisService.hashRemove(RedisConfig.UserInfoKey,user.getId().toString());
        return userMapper.updateUserProfile(user);
    }

}
