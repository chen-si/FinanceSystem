package com.heu.finance.service.loan;

import com.heu.finance.pojo.loan.LoanInfo;
import com.heu.finance.pojo.loan.LoanInfoRemindPay;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanInfoService {

    public List<LoanInfo> selectLoanInfoAll();

    //按id查找
    public LoanInfoRemindPay selectById(Integer id);

    //排序
    public List<LoanInfo> selectLoanInfoOrderBy(String orderBy);
}
