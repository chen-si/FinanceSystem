package com.heu.finance.service.impl.normal.tools;

import com.heu.finance.mapper.normal.tools.RecordFlowMapper;
import com.heu.finance.pojo.tools.RecordFlow;
import com.heu.finance.service.normal.tools.RecordFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordFlowServiceImpl implements RecordFlowService {

    @Autowired
    private RecordFlowMapper recordFlowMapper;

    @Override
    public List<RecordFlow> selectRecord(Integer id) {
        return recordFlowMapper.selectRecord(id);
    }
}
