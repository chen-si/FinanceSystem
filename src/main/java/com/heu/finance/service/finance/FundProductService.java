package com.heu.finance.service.finance;

import com.heu.finance.pojo.finance.FundProduct;

import java.util.List;

public interface FundProductService {
    List<FundProduct> selectAllFundProduct();

    void insertFundProduct(FundProduct fundProduct);

    FundProduct getFundProductById(Integer id);

    int updateFundProductInfos(FundProduct fundProduct);

    int deleteFundProductById(Integer id);
}
