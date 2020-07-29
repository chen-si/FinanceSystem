package com.heu.finance.service.impl.tools;

import com.heu.finance.mapper.tools.RecordFlowMapper;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.service.tools.RecordFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordFlowServiceImpl implements RecordFlowService {
    private RecordFlowMapper recordFlowMapper;

    @Autowired
    public void setRecordFlowMapper(RecordFlowMapper recordFlowMapper) {
        this.recordFlowMapper = recordFlowMapper;
    }

    @Override
    public List<RecordFlow> selectRecord(Integer id) {
        return recordFlowMapper.selectRecord(id);
    }

    @Override
    public int insertRecord(RecordFlow recordFlow) {
        return recordFlowMapper.insertRecord(recordFlow);
    }
}
