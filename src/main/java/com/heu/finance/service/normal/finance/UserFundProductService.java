package com.heu.finance.service.normal.finance;

import com.heu.finance.pojo.finance.UserFundProduct;

public interface UserFundProductService {
    int addUserFundProduct(UserFundProduct userFundProduct);

    int updateUserFundProductStatus(Integer status,Integer id);

    int deleteFundProductById(Integer id);
}
