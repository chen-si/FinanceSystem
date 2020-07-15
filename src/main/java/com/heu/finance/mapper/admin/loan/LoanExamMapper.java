package com.heu.finance.mapper.admin.loan;

import com.heu.finance.pojo.LoanExam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanExamMapper {

    public List<LoanExam> selectLoanExamAll();

    //通过审核
    public int updateApplyStatus(LoanExam loanExam);

    //未通过审核
    public int updateApplyStatusNotPass(LoanExam loanExam);
}
