package com.heu.finance.service.impl.loan;

import com.heu.finance.mapper.loan.LoanInfoMapper;
import com.heu.finance.pojo.loan.LoanInfo;
import com.heu.finance.pojo.loan.LoanInfoRemindPay;
import com.heu.finance.service.loan.LoanInfoService;
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

    @Override
    public LoanInfoRemindPay selectById(Integer id) {
        return loanInfoMapper.selectById(id);
    }

    //排序

    @Override
    public List<LoanInfo> selectLoanInfoOrderBy(String orderBy) {
        return loanInfoMapper.selectLoanInfoOrderBy(orderBy);
    }
}
