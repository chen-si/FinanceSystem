package com.heu.finance.mapper.normal.finance;

import com.heu.finance.pojo.finance.FundProduct;
import com.heu.finance.pojo.finance.UserFundProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserFundProductMapper {
    int addUserFundProduct(UserFundProduct userFundProduct);

    int updateUserFundProductStatus(Integer status,Integer id);

    int deleteFundProductById(Integer id);

    List<UserFundProduct> selectUserFundProductByUserId(Integer userId);

    List<UserFundProduct> selectUserFundProductByUserIdOrderBy(Integer userId,String orderBy);
}
