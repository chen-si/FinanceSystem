package com.heu.finance.service.tools;

import com.heu.finance.pojo.tools.RecordFlow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecordFlowService {

    public List<RecordFlow> selectRecord(Integer id);

    int insertRecord(RecordFlow recordFlow);
}
