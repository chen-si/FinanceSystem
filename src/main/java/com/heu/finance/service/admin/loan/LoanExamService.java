package com.heu.finance.service.admin.loan;

import com.heu.finance.pojo.loan.LoanExam;
import com.heu.finance.pojo.personal.MyLoan;
import com.heu.finance.pojo.tools.UserApplyLoan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanExamService {

    public List<LoanExam> selectLoanExamAll();

    //通过审核
    public int updateApplyStatus(LoanExam loanExam);

    //未通过审核
    public int updateApplyStausNotPass(LoanExam loanExam);

    //用户申请网贷
    public int applyloan(UserApplyLoan userApplyLoan);

    //我的借贷
    public List<MyLoan> myLoan(Integer id);

    //还款
    public int repayLoan(Integer id);
}
