package com.heu.finance.service.normal.finance;

import com.heu.finance.pojo.finance.PayMoney;
import com.heu.finance.pojo.finance.UserPayMoney;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserPayMoneyService {
    //投资
    public int addUserPayMoney(UserPayMoney userPayMoney);

    List<UserPayMoney> selectUserPayMoneyByUserId(Integer userId);

    int updateUserPayMoneyStatus(Integer id, Integer status);
}