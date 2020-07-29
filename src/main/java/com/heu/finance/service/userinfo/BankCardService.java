package com.heu.finance.service.userinfo;

import com.heu.finance.pojo.userinfo.BankCard;

import java.util.List;

public interface BankCardService {
    List<BankCard> selectAllBankCard();

    int insertBankCard(BankCard bankCard,Integer userId);

    BankCard getBankCardById(Integer id);

    int updateBankCardInfos(BankCard bankCard);

    int deleteBankCardById(Integer id);

    List<BankCard> getBankCardByUserId(Integer userId);
}
