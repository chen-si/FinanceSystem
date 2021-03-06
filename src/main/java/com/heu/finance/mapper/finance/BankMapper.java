package com.heu.finance.mapper.finance;

import com.heu.finance.pojo.finance.Bank;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankMapper {
    List<Bank> selectBankAll();
    int addBank(Bank bank);
    Bank selectBankById(Integer id);
    int updateBank(Bank bank);
    int deleteBankById(Integer id);
}
