package com.heu.finance.service.impl.finance;

import com.heu.finance.mapper.finance.UserPayMoneyMapper;
import com.heu.finance.pojo.finance.UserPayMoney;
import com.heu.finance.service.finance.UserPayMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPayMoneyImpl implements UserPayMoneyService {

    @Autowired
    private UserPayMoneyMapper userPayMoneyMapper;

    @Override
    public int addUserPayMoney(UserPayMoney userPayMoney) {
        return userPayMoneyMapper.addUserPayMoney(userPayMoney);
    }

    @Override
    public List<UserPayMoney> selectUserPayMoneyByUserId(Integer userId) {
        return userPayMoneyMapper.selectUserPayMoneyByUserId(userId);
    }

    @Override
    public List<UserPayMoney> selectUserPayMoneyByUserIdOrderBy(Integer userId, String orderBy) {
        return userPayMoneyMapper.selectUserPayMoneyByUserIdOrderBy(userId, orderBy);
    }

    @Override
    public int updateUserPayMoneyStatus(Integer id, Integer status) {
        return userPayMoneyMapper.updateUserPayMoneyStatus(id,status);
    }
}
