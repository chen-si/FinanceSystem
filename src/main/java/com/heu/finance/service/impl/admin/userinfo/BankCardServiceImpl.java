package com.heu.finance.service.impl.admin.userinfo;

import com.heu.finance.mapper.admin.userinfo.BankCardMapper;
import com.heu.finance.pojo.admin.userinfo.BankCard;
import com.heu.finance.service.admin.userinfo.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardServiceImpl implements BankCardService {
    private BankCardMapper bankCardMapper;

    @Autowired
    public void setBankCardMapper(BankCardMapper bankCardMapper) {
        this.bankCardMapper = bankCardMapper;
    }

    @Override
    public List<BankCard> selectAllBankCard() {
        return bankCardMapper.selectAllBankCard();
    }

    @Override
    public void insertBankCard(BankCard bankCard) {
        bankCardMapper.insertBankCard(bankCard);
    }

    @Override
    public BankCard getBankCardById(Integer id) {
        return bankCardMapper.getBankCardById(id);
    }

    @Override
    public int updateBankCardInfos(BankCard bankCard) {
        return bankCardMapper.updateBankCardInfos(bankCard);
    }

    @Override
    public int deleteBankCardById(Integer id) {
        return bankCardMapper.deleteBankCardById(id);
    }
}
