package com.heu.finance.service.impl;

import com.heu.finance.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key,expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key,delta);
    }

    @Override
    public void hashSet(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key,field,value);
    }

    @Override
    public String hashGet(String key, String field) {
        Object val = stringRedisTemplate.opsForHash().get(key,field);
        return val == null?null:val.toString();
    }

    @Override
    public void hashRemove(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void hashRemove(String key, String field) {
        stringRedisTemplate.opsForHash().delete(key,field);
    }
}
