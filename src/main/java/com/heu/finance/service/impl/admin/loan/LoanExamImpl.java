package com.heu.finance.service.impl.admin.loan;

import com.heu.finance.mapper.admin.LoanExamMapper;
import com.heu.finance.mapper.admin.LoginMapper;
import com.heu.finance.pojo.LoanExam;
import com.heu.finance.service.LoanExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanExamImpl implements LoanExamService {

    @Autowired
    private LoanExamMapper loanExamMapper;

    @Override
    public List<LoanExam> selectLoanExamAll() {
        return loanExamMapper.selectLoanExamAll();
    }
//审核通过
    @Override
    public int updateApplyStatus(LoanExam loanExam) {
        return loanExamMapper.updateApplyStatus(loanExam);
    }

    //审核未通过


    @Override
    public int updateApplyStausNotPass(LoanExam loanExam) {
        return loanExamMapper.updateApplyStatusNotPass(loanExam);
    }
}
