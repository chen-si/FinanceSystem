package com.heu.finance.service.impl.admin.finance;

import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.admin.finance.FundProductMapper;
import com.heu.finance.pojo.finance.FundProduct;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.admin.finance.FundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundProductServiceImpl implements FundProductService {
    private FundProductMapper fundProductMapper;
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

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
        FundProduct fundProduct = JSON.parseObject(
                redisService.hashGet(RedisConfig.FundProductKey,id.toString()),FundProduct.class);
        if( fundProduct == null ){
            fundProduct = fundProductMapper.getFundProductById(id);
            redisService.hashSet(
                    RedisConfig.FundProductKey,id.toString(),JSON.toJSON(fundProduct).toString());
        }
        return fundProduct;
    }

    @Override
    public int updateFundProductInfos(FundProduct fundProduct) {
        redisService.hashRemove(RedisConfig.FundProductKey,fundProduct.getId().toString());
        return fundProductMapper.updateFundProductInfos(fundProduct);
    }

    @Override
    public int deleteFundProductById(Integer id) {
        redisService.hashRemove(RedisConfig.FundProductKey,id.toString());
        return fundProductMapper.deleteFundProductById(id);
    }
}
