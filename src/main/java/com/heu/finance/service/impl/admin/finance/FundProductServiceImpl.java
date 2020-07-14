package com.heu.finance.service.impl.admin.finance;

import com.heu.finance.mapper.admin.finance.FundProductMapper;
import com.heu.finance.pojo.admin.finance.FundProduct;
import com.heu.finance.service.admin.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundProductServiceImpl implements FundProductService {
    private FundProductMapper fundProductMapper;

    @Autowired
    public void setFundProductMapper(FundProductMapper fundProductMapper) {
        this.fundProductMapper = fundProductMapper;
    }

    @Override
    public List<FundProduct> selectAllFundProduct() {
        return fundProductMapper.selectAllFundProduct();
    }

    @Override
    public void insertFundProduct(FundProduct fundProduct) {
        fundProductMapper.insertFundProduct(fundProduct);
    }

    @Override
    public FundProduct getFundProductById(Integer id) {
        return fundProductMapper.getFundProductById(id);
    }

    @Override
    public int updateFundProductInfos(FundProduct fundProduct) {
        return fundProductMapper.updateFundProductInfos(fundProduct);
    }

    @Override
    public int deleteFundProductById(Integer id) {
        return fundProductMapper.deleteFundProductById(id);
    }
}
