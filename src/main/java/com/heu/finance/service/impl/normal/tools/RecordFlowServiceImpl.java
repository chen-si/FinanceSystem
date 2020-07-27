package com.heu.finance.service.impl.normal.tools;

import com.alibaba.fastjson.JSONArray;
import com.heu.finance.common.RedisConfig;
import com.heu.finance.mapper.normal.tools.RecordFlowMapper;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.service.RedisService;
import com.heu.finance.service.normal.tools.RecordFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordFlowServiceImpl implements RecordFlowService {
    private RecordFlowMapper recordFlowMapper;
    private RedisService redisService;

    @Autowired
    public void setRecordFlowMapper(RecordFlowMapper recordFlowMapper) {
        this.recordFlowMapper = recordFlowMapper;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public List<RecordFlow> selectRecord(Integer id) {
        List<RecordFlow> recordFlowList =
                JSONArray.parseArray(redisService.get(RedisConfig.RecordFlowPrefix+id),RecordFlow.class);
        if (recordFlowList == null){
            recordFlowList = recordFlowMapper.selectRecord(id);
            redisService.set(RedisConfig.RecordFlowPrefix+id,JSONArray.toJSON(recordFlowList).toString());
            redisService.expire(RedisConfig.RecordFlowPrefix+id,240);
        }
        return recordFlowList;
    }

    @Override
    public int insertRecord(RecordFlow recordFlow) {
        redisService.remove(RedisConfig.RecordFlowPrefix+recordFlow.getUserId());
        return recordFlowMapper.insertRecord(recordFlow);
    }
}
