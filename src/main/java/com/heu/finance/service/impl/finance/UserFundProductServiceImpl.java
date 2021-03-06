package com.heu.finance.service.impl.finance;

import com.heu.finance.mapper.finance.UserFundProductMapper;
import com.heu.finance.pojo.finance.UserFundProduct;
import com.heu.finance.service.finance.UserFundProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFundProductServiceImpl implements UserFundProductService {
    private UserFundProductMapper userFundProductMapper;

    @Autowired
    public void setUserFundProductMapper(UserFundProductMapper userFundProductMapper) {
        this.userFundProductMapper = userFundProductMapper;
    }


    @Override
    public int addUserFundProduct(UserFundProduct userFundProduct) {
        return userFundProductMapper.addUserFundProduct(userFundProduct);
    }

    @Override
    public int updateUserFundProductStatus(Integer status, Integer id) {
        return userFundProductMapper.updateUserFundProductStatus(status,id);
    }

    @Override
    public int deleteFundProductById(Integer id) {
        return userFundProductMapper.deleteFundProductById(id);
    }

    @Override
    public List<UserFundProduct> selectUserFundProductByUserIdOrderBy(Integer userId, String orderBy) {
        return userFundProductMapper.selectUserFundProductByUserIdOrderBy(userId, orderBy);
    }

    @Override
    public List<UserFundProduct> selectUserFundProductByUserId(Integer userId) {
        return userFundProductMapper.selectUserFundProductByUserId(userId);
    }
}
