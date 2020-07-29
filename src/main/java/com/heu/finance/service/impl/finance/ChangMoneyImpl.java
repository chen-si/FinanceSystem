package com.heu.finance.service.impl.finance;


import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.finance.Change_MoneyMapper;
import com.heu.finance.pojo.finance.ChangeMoney;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.finance.ChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangMoneyImpl implements ChangeMoneyService {
    private Change_MoneyMapper change_moneyMapper;
    private RedisService redisService;

    @Autowired
    public void setChange_moneyMapper(Change_MoneyMapper change_moneyMapper) {
        this.change_moneyMapper = change_moneyMapper;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public List<ChangeMoney> selectChangeMoneyAll() {
        return change_moneyMapper.selectChangeMoneyAll();
    }

    @Override
    public int addChangeMoney(ChangeMoney changeMoney) {
        return change_moneyMapper.addChangeMoney(changeMoney);
    }

    @Override
    public ChangeMoney selectChangeMoneyById(Integer id) {
        ChangeMoney changeMoney = JSON.parseObject(
                redisService.hashGet(RedisConfig.ChangeMoneyKey,id.toString()),ChangeMoney.class);

        if (changeMoney == null){
            changeMoney = change_moneyMapper.selectChangeMoneyById(id);
            redisService.hashSet(
                    RedisConfig.ChangeMoneyKey,id.toString(),JSON.toJSON(changeMoney).toString());
        }
        return changeMoney;
    }

    @Override
    public int alterChangeMoney(ChangeMoney changeMoney) {
        redisService.hashRemove(RedisConfig.ChangeMoneyKey,changeMoney.getId().toString());
        return change_moneyMapper.alterChangeMoney(changeMoney);
    }

    @Override
    public int deleteChangeMoney(Integer id) {
        redisService.hashRemove(RedisConfig.ChangeMoneyKey,id.toString());
        return change_moneyMapper.deleteChangeMoney(id);
    }

    @Override
    public List<ChangeMoney> selectChangeMoneyOrderBy(String orderBy) {
        return change_moneyMapper.selectChangeMoneyOrderBy(orderBy);
    }
}
