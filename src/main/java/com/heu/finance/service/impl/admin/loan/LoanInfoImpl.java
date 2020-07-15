package com.heu.finance.service.impl.admin.loan;

import com.heu.finance.mapper.admin.LoanInfoMapper;
import com.heu.finance.pojo.LoanInfo;
import com.heu.finance.service.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanInfoImpl implements LoanInfoService {
    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Override
    public List<LoanInfo> selectLoanInfoAll() {
        return loanInfoMapper.selectLoanInfoAll();
    }
}
