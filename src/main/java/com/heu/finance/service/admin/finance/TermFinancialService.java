package com.heu.finance.service.admin.finance;

import com.heu.finance.pojo.finance.TermFinancial;

import java.util.List;

public interface TermFinancialService {
    List<TermFinancial> selectAllTermFinancial();

    void insertTermFinancial(TermFinancial termFinancial);

    TermFinancial getTermFinancialById(Integer id);

    int updateTermFinancialInfos(TermFinancial termFinancial);

    int deleteTermFinancialById(Integer id);
}
