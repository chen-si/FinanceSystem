package com.heu.finance.mapper.normal.finance;


import com.heu.finance.pojo.finance.PayMoney;
import com.heu.finance.pojo.finance.UserPayMoney;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserPayMoneyMapper {
    //投资
    public int addUserPayMoney(UserPayMoney userPayMoney);

    List<UserPayMoney> selectUserPayMoneyByUserId(Integer userId);

    int updateUserPayMoneyStatus(Integer id, Integer status);
}
