package com.heu.finance.service.admin.finance;

import com.demo.pojo.Bank;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface BankService {
    public List<Bank> selectBankAll();
    public int addBank(Bank bank);
    public Bank selectBankById(Integer id);
    public int updateBank(Bank bank);
    public int deleteBankById(Integer id);

}
