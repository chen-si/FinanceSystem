package com.heu.finance.service.finance;

import com.heu.finance.pojo.finance.Bank;

import java.util.List;

public interface BankService {
    List<Bank> selectBankAll();
    int addBank(Bank bank);
    Bank selectBankById(Integer id);
    int updateBank(Bank bank);
    int deleteBankById(Integer id);
}
