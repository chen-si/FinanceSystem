package com.heu.finance.service.impl.normal.finance;

import com.heu.finance.mapper.normal.finance.UserChangeMoneyMapper;
import com.heu.finance.pojo.finance.UserChangeMoney;
import com.heu.finance.service.normal.finance.UserChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChangeMoneyImpl implements UserChangeMoneyService {

    @Autowired
    private UserChangeMoneyMapper userChangeMoneyMapper;


    @Override
    public int addUserChangeMoney(UserChangeMoney userChangeMoney) {
        return userChangeMoneyMapper.addUserChangeMoney(userChangeMoney);
    }

    @Override
    public List<UserChangeMoney> selectUserChangeMoneyByUserId(Integer userId) {
        return userChangeMoneyMapper.selectUserChangeMoneyByUserId(userId);
    }

    @Override
    public List<UserChangeMoney> selectUserChangeMoneyByUserIdOrderBy(Integer userId, String orderBy) {
        return userChangeMoneyMapper.selectUserChangeMoneyByUserIdOrderBy(userId, orderBy);
    }

    @Override
    public int updateUserChangeMoneyStatus(Integer id, Integer status) {
        return userChangeMoneyMapper.updateUserChangeMoneyStatus(id, status);
    }

}
