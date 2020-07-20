package com.heu.finance.mapper.normal.finance;


import com.heu.finance.pojo.finance.UserChangeMoney;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserChangeMoneyMapper {
    //投资
    public int addUserChangeMoney(UserChangeMoney userChangeMoney);

    List<UserChangeMoney> selectUserChangeMoneyByUserId(Integer userId);

    int updateUserChangeMoneyStatus(Integer id,Integer status);
}