package com.heu.finance.service.impl.admin.finance;


import com.heu.finance.mapper.admin.finance.Change_MoneyMapper;
import com.heu.finance.pojo.admin.finance.ChangeMoney;
import com.heu.finance.service.admin.finance.ChangeMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangMoneyImpl implements ChangeMoneyService {

    @Autowired
    private Change_MoneyMapper change_moneyMapper;

    @Override
    public List<ChangeMoney> selectChangeMoneyAll() {

        return change_moneyMapper.selectChangeMoneyAll();
    }

    @Override
    public int addChangeMoney(ChangeMoney changeMoney) {
        return change_moneyMapper.addChangeMoney(changeMoney);
    }

    @Override
    public ChangeMoney selectChangeMoneyById(Integer id) {
        return change_moneyMapper.selectChangeMoneyById(id);
    }

    @Override
    public int alterChangeMoney(ChangeMoney changeMoney) {
        return change_moneyMapper.alterChangeMoney(changeMoney);
    }

    @Override
    public int deleteChangeMoney(Integer id) {
        return change_moneyMapper.deleteChangeMoney(id);
    }
}
