package com.heu.finance.mapper.admin.loan;

import com.heu.finance.pojo.loan.LoanExam;
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
}
