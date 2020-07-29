package com.heu.finance.service.impl.finance;


import com.heu.finance.mapper.finance.BankMapper;
import com.heu.finance.pojo.finance.Bank;
import com.heu.finance.service.finance.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    private BankMapper bankMapper;

    @Autowired
    public void setBankMapper(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    @Override
    public List<Bank> selectBankAll() {
        return bankMapper.selectBankAll();
    }

    @Override
    public int addBank(Bank bank) {
        return bankMapper.addBank(bank);
    }

    @Override
    public Bank selectBankById(Integer id) {
        return bankMapper.selectBankById(id);
    }

    @Override
    public int updateBank(Bank bank){
        return bankMapper.updateBank(bank);
    }

    @Override
    public int deleteBankById(Integer id){
        return bankMapper.deleteBankById(id);
    }
}
