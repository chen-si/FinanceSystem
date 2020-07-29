package com.heu.finance.service.impl.userinfo;

import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.userinfo.BankCardMapper;
import com.heu.finance.pojo.userinfo.BankCard;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.userinfo.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardServiceImpl implements BankCardService {
    private BankCardMapper bankCardMapper;
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setBankCardMapper(BankCardMapper bankCardMapper) {
        this.bankCardMapper = bankCardMapper;
    }

    @Override
    public List<BankCard> selectAllBankCard() {
        return bankCardMapper.selectAllBankCard();
    }

    @Override
    public int insertBankCard(BankCard bankCard, Integer userId) {
        return bankCardMapper.insertBankCard(bankCard.getCardBank(),bankCard.getType(),
                bankCard.getCardNum(),userId);
    }

    @Override
    public BankCard getBankCardById(Integer id) {
        BankCard bankCard = JSON.parseObject(
                redisService.hashGet(RedisConfig.BankCardKey,id.toString()),BankCard.class);
        if (bankCard == null){
            bankCard = bankCardMapper.getBankCardById(id);
            redisService.hashSet(RedisConfig.BankCardKey,id.toString(),JSON.toJSON(bankCard).toString());
        }
        return bankCard;
    }

    @Override
    public int updateBankCardInfos(BankCard bankCard) {
        redisService.hashRemove(RedisConfig.BankCardKey,bankCard.getId().toString());
        return bankCardMapper.updateBankCardInfos(bankCard);
    }

    @Override
    public int deleteBankCardById(Integer id) {
        redisService.hashRemove(RedisConfig.BankCardKey,id.toString());
        return bankCardMapper.deleteBankCardById(id);
    }

    @Override
    public List<BankCard> getBankCardByUserId(Integer userId) {
        return bankCardMapper.getBankCardByUserId(userId);
    }
}
