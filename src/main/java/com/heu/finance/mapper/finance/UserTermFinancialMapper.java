package com.heu.finance.mapper.finance;

import com.heu.finance.pojo.finance.UserTermFinancial;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserTermFinancialMapper {
    //期限理财购买
    public boolean insertUserTermFinancial(UserTermFinancial userTermFinancial);

    //撤销期限理财
    public boolean updateUserTermFinancialStatus(Integer id,Integer status);

    public List<UserTermFinancial> selectUserTermFinancialByUserId(Integer userId);

    public List<UserTermFinancial> selectUserTermFinancialByUserIdOrderBy(Integer userId,String orderBy);
}
