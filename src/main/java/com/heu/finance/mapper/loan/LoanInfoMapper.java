package com.heu.finance.mapper.loan;

import com.heu.finance.pojo.loan.LoanInfo;
import com.heu.finance.pojo.loan.LoanInfoRemindPay;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoanInfoMapper {
    public List<LoanInfo> selectLoanInfoAll();

    //按id查找
    public LoanInfoRemindPay selectById(Integer id);

    //排序
    public List<LoanInfo> selectLoanInfoOrderBy(String orderBy);
}
