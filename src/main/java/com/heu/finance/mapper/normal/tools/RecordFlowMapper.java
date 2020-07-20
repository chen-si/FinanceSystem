package com.heu.finance.mapper.normal.tools;

import com.heu.finance.pojo.tools.RecordFlow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordFlowMapper {

    public List<RecordFlow> selectRecord(Integer id);
}
