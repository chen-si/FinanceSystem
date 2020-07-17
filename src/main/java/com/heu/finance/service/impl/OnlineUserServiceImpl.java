package com.heu.finance.service.impl;

import com.heu.finance.common.RedisConfig;
import com.heu.finance.service.OnlineUserService;
import com.heu.finance.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineUserServiceImpl implements OnlineUserService {
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void addUser(String username){
        redisService.set(RedisConfig.UserStatusPrefix + username, RedisConfig.OnlineKey);
        redisService.expire(RedisConfig.UserStatusPrefix+username,30*60);
    }

    @Override
    public void userLogout(String username){
        redisService.set(RedisConfig.UserStatusPrefix + username, RedisConfig.OfflineKey);
    }

    @Override
    public boolean isLogin(String username){

        return RedisConfig.OnlineKey.equals(redisService.get(RedisConfig.UserStatusPrefix + username));
    }
}
