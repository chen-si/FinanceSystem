package com.heu.finance.service.impl.finance;

import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.finance.PayMoneyMapper;
import com.heu.finance.pojo.finance.PayMoney;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.finance.PayMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayMoneyImpl implements PayMoneyService {
    private PayMoneyMapper payMoneyMapper;
    private RedisService redisService;

    @Autowired
    public void setPayMoneyMapper(PayMoneyMapper payMoneyMapper) {
        this.payMoneyMapper = payMoneyMapper;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public List<PayMoney> selectPayMoneyAll() {
        return payMoneyMapper.selectPayMoneyAll();
    }

    @Override
    public int addPayMoney(PayMoney payMoney) {
        return payMoneyMapper.addPayMoney(payMoney);
    }

    //修改回显

    @Override
    public PayMoney selectPayMoneyById(Integer id) {
        PayMoney payMoney = JSON.parseObject(
                redisService.hashGet(RedisConfig.PayMoneyKey,id.toString()),PayMoney.class);
        if(payMoney == null){
            payMoney =  payMoneyMapper.selectPayMoneyById(id);
            redisService.hashSet(
                    RedisConfig.PayMoneyKey,id.toString(),JSON.toJSON(payMoney).toString());
        }
        return payMoney;
    }

    //更新

    @Override
    public int updatePayMoney(PayMoney payMoney) {
        redisService.hashRemove(RedisConfig.PayMoneyKey,payMoney.getId().toString());
        return payMoneyMapper.updatePayMoney(payMoney);
    }

    //删除

    @Override
    public int deletePayMoney(Integer id) {
        redisService.hashRemove(RedisConfig.PayMoneyKey,id.toString());
        return payMoneyMapper.deletePayMoney(id);
    }

    @Override
    public List<PayMoney> selectPayMoneyOrderBy(String orderBy) {
        return payMoneyMapper.selectPayMoneyOrderBy(orderBy);
    }
}
