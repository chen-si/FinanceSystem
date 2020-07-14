package com.heu.finance.mapper.admin.finance;

import com.heu.finance.pojo.admin.finance.TermFinancial;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface TermFinancialMapper {
    List<TermFinancial> selectAllTermFinancial();

    void insertTermFinancial(TermFinancial termFinancial);

    TermFinancial getTermFinancialById(Integer id);

    int updateTermFinancialInfos(TermFinancial termFinancial);

    int deleteTermFinancialById(Integer id);
}
