package com.heu.finance.service.impl.finance;

import com.heu.finance.mapper.finance.UserTermFinancialMapper;
import com.heu.finance.pojo.finance.UserTermFinancial;
import com.heu.finance.service.finance.UserTermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTermFinancialServiceImpl implements UserTermFinancialService {
    private UserTermFinancialMapper userTermFinancialMapper;

    @Autowired
    public void setUserTermFinancialMapper(UserTermFinancialMapper userTermFinancialMapper) {
        this.userTermFinancialMapper = userTermFinancialMapper;
    }

    @Override
    public List<UserTermFinancial> selectUserTermFinancialByUserId(Integer userId) {
        return userTermFinancialMapper.selectUserTermFinancialByUserId(userId);
    }

    @Override
    public boolean insertUserTermFinancial(UserTermFinancial userTermFinancial) {
        return userTermFinancialMapper.insertUserTermFinancial(userTermFinancial);
    }

    @Override
    public boolean updateUserTermFinancialStatus(Integer id,Integer status) {
        return userTermFinancialMapper.updateUserTermFinancialStatus(id,status);
    }

    @Override
    public List<UserTermFinancial> selectUserTermFinancialByUserIdOrderBy(Integer userId, String orderBy) {
        return userTermFinancialMapper.selectUserTermFinancialByUserIdOrderBy(userId, orderBy);
    }
}
