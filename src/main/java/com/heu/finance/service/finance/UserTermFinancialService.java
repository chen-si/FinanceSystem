package com.heu.finance.service.finance;

import com.heu.finance.pojo.finance.UserTermFinancial;

import java.util.List;

public interface UserTermFinancialService {
    public List<UserTermFinancial> selectUserTermFinancialByUserId(Integer userId);

    public boolean insertUserTermFinancial(UserTermFinancial userTermFinancial);

    public boolean updateUserTermFinancialStatus(Integer id,Integer status);

    public List<UserTermFinancial> selectUserTermFinancialByUserIdOrderBy(Integer userId,String orderBy);
}
