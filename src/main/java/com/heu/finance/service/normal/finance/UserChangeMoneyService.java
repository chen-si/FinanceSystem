package com.heu.finance.service.normal.finance;

import com.heu.finance.pojo.finance.UserChangeMoney;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserChangeMoneyService {
    //投资
    public int addUserChangeMoney(UserChangeMoney userChangeMoney);

    List<UserChangeMoney> selectUserChangeMoneyByUserId(Integer userId);

    List<UserChangeMoney> selectUserChangeMoneyByUserIdOrderBy(Integer userId,String orderBy);

    int updateUserChangeMoneyStatus(Integer id,Integer status);
}
