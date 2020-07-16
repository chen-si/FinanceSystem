package com.heu.finance.mapper.admin.userinfo;

import com.heu.finance.pojo.userinfo.BankCard;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BankCardMapper {
    List<BankCard> selectAllBankCard();

    void insertBankCard(BankCard bankCard);

    BankCard getBankCardById(Integer id);

    int updateBankCardInfos(BankCard bankCard);

    int deleteBankCardById(Integer id);
}
