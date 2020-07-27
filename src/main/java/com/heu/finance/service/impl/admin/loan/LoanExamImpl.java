package com.heu.finance.service.impl.admin.loan;


import com.heu.finance.mapper.admin.loan.LoanExamMapper;
import com.heu.finance.pojo.loan.LoanExam;
import com.heu.finance.pojo.personal.MyLoan;
import com.heu.finance.pojo.tools.UserApplyLoan;
import com.heu.finance.service.admin.loan.LoanExamService;
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

    //用户申请网贷
    @Override
    public int applyloan(UserApplyLoan userApplyLoan) {
        return loanExamMapper.applyloan(userApplyLoan);
    }

    //我的借贷
    @Override
    public List<MyLoan> myLoan(Integer id) {
        return loanExamMapper.myLoan(id);
    }

    @Override
    public List<MyLoan> myLoanOrderBy(Integer id, String orderBy) {
        return loanExamMapper.myLoanOrderBy(id, orderBy);
    }

    //还款

    @Override
    public int repayLoan(Integer id) {
        return loanExamMapper.repayLoan(id);
    }

    //排序

    @Override
    public List<LoanExam> selectLoanExamOrderBy(String orderBy) {
        return loanExamMapper.selectLoanExamOrderBy(orderBy);
    }
}
