package com.heu.finance.service.impl.finance;

import com.alibaba.fastjson.JSON;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.finance.TermFinancialMapper;
import com.heu.finance.pojo.finance.TermFinancial;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.finance.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermFinancialServiceImpl implements TermFinancialService {
    private TermFinancialMapper termFinancialMapper;
    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Autowired
    public void setTermFinancialMapper(TermFinancialMapper termFinancialMapper) {
        this.termFinancialMapper = termFinancialMapper;
    }

    @Override
    public List<TermFinancial> selectAllTermFinancial() {
        return termFinancialMapper.selectAllTermFinancial();
    }

    @Override
    public void insertTermFinancial(TermFinancial termFinancial) {
        termFinancialMapper.insertTermFinancial(termFinancial);
    }

    @Override
    public TermFinancial getTermFinancialById(Integer id) {
        TermFinancial termFinancial = JSON.parseObject(
                redisService.hashGet(
                        RedisConfig.TermFinancialKey,id.toString()),TermFinancial.class);

        if (termFinancial == null){
            termFinancial = termFinancialMapper.getTermFinancialById(id);
            redisService.hashSet(RedisConfig.TermFinancialKey,id.toString(),
                    JSON.toJSON(termFinancial).toString());
        }
        return termFinancial;
    }

    @Override
    public int updateTermFinancialInfos(TermFinancial termFinancial) {
        redisService.hashRemove(RedisConfig.TermFinancialKey,termFinancial.getId().toString());
        return termFinancialMapper.updateTermFinancialInfos(termFinancial);
    }

    @Override
    public int deleteTermFinancialById(Integer id) {
        redisService.hashRemove(RedisConfig.TermFinancialKey,id.toString());
        return termFinancialMapper.deleteTermFinancialById(id);
    }
}
