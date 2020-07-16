package com.heu.finance.mapper.normal.finance;

import com.heu.finance.pojo.finance.UserFundProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserFundProductMapper {
    int addUserFundProduct(UserFundProduct userFundProduct);

    int updateUserFundProductStatus(Integer status,Integer id);

    int deleteFundProductById(Integer id);
}
