package com.heu.finance.service.admin.userinfo;

import com.heu.finance.pojo.userinfo.BankCard;

import java.util.List;

public interface BankCardService {
    List<BankCard> selectAllBankCard();

    void insertBankCard(BankCard bankCard);

    BankCard getBankCardById(Integer id);

    int updateBankCardInfos(BankCard bankCard);

    int deleteBankCardById(Integer id);
}
