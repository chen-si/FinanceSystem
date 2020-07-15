package com.heu.finance.mapper.admin.loan;

import com.heu.finance.pojo.LoanInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanInfoMapper {

    public List<LoanInfo> selectLoanInfoAll();
}
