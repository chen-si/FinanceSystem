package com.heu.finance.mapper.admin.finance;


import com.heu.finance.pojo.admin.finance.FundProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FundProductMapper {
    List<FundProduct> selectAllFundProduct();

    void insertFundProduct(FundProduct fundProduct);

    FundProduct getFundProductById(Integer id);

    int updateFundProductInfos(FundProduct fundProduct);

    int deleteFundProductById(Integer id);
}
