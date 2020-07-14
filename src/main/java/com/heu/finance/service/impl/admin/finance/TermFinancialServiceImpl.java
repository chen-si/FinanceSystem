package com.heu.finance.service.impl.admin.finance;

import com.heu.finance.mapper.admin.finance.TermFinancialMapper;
import com.heu.finance.pojo.admin.finance.TermFinancial;
import com.heu.finance.service.admin.finance.TermFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermFinancialServiceImpl implements TermFinancialService {
    private TermFinancialMapper termFinancialMapper;

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
        return termFinancialMapper.getTermFinancialById(id);
    }

    @Override
    public int updateTermFinancialInfos(TermFinancial termFinancial) {
        return termFinancialMapper.updateTermFinancialInfos(termFinancial);
    }

    @Override
    public int deleteTermFinancialById(Integer id) {
        return termFinancialMapper.deleteTermFinancialById(id);
    }
}
