package com.heu.finance.mapper.admin.loan;

import com.heu.finance.pojo.loan.LoanInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoanInfoMapper {
    public List<LoanInfo> selectLoanInfoAll();
}
