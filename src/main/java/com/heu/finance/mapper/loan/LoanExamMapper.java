package com.heu.finance.mapper.loan;

import com.heu.finance.pojo.loan.LoanExam;
import com.heu.finance.pojo.personal.MyLoan;
import com.heu.finance.pojo.tools.UserApplyLoan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoanExamMapper {

    public List<LoanExam> selectLoanExamAll();

    //通过审核
    public int updateApplyStatus(LoanExam loanExam);

    //未通过审核
    public int updateApplyStatusNotPass(LoanExam loanExam);

    //用户申请网贷
    public int applyloan(UserApplyLoan userApplyLoan);

    //我的借贷
    public List<MyLoan> myLoan(Integer id);

    public List<MyLoan> myLoanOrderBy(Integer id,String orderBy);

    //还款
    public int repayLoan(Integer id);

    //排序
    public List<LoanExam> selectLoanExamOrderBy(String orderBy);
}
