package com.heu.finance.service.admin.finance;

import com.heu.finance.pojo.admin.finance.Bank;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface BankService {
    List<Bank> selectBankAll();
    int addBank(Bank bank);
    Bank selectBankById(Integer id);
    int updateBank(Bank bank);
    int deleteBankById(Integer id);
}
