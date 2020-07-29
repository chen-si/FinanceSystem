package com.heu.finance.mapper.finance;


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

    List<UserChangeMoney> selectUserChangeMoneyByUserIdOrderBy(Integer userId,String orderBy);

    int updateUserChangeMoneyStatus(Integer id,Integer status);
}
