package com.heu.finance.service.normal.finance;

import com.heu.finance.pojo.finance.UserFundProduct;

import java.util.List;

public interface UserFundProductService {
    int addUserFundProduct(UserFundProduct userFundProduct);

    int updateUserFundProductStatus(Integer status,Integer id);

    int deleteFundProductById(Integer id);

    List<UserFundProduct> selectUserFundProductByUserIdOrderBy(Integer userId,String orderBy);

    List<UserFundProduct> selectUserFundProductByUserId(Integer userId);
}
