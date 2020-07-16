package com.heu.finance.service.admin.loan;


import com.heu.finance.pojo.admin.loan.LoanExam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanExamService {

    public List<LoanExam> selectLoanExamAll();

    //通过审核
    public int updateApplyStatus(LoanExam loanExam);

    //未通过审核
    public int updateApplyStausNotPass(LoanExam loanExam);
}
