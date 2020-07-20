package com.heu.finance.service.impl.normal.finance;

import com.heu.finance.mapper.normal.finance.UserPayMoneyMapper;
import com.heu.finance.pojo.finance.PayMoney;
import com.heu.finance.pojo.finance.UserPayMoney;
import com.heu.finance.service.normal.finance.UserPayMoneyService;
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
    public int updateUserPayMoneyStatus(Integer id, Integer status) {
        return userPayMoneyMapper.updateUserPayMoneyStatus(id,status);
    }
}
