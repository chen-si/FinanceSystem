package com.heu.finance.service.admin.loan;

import com.heu.finance.pojo.LoanInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanInfoService {

    public List<LoanInfo> selectLoanInfoAll();
}
