package com.heu.finance.service.impl.admin.finance;

import com.heu.finance.mapper.admin.finance.PayMoneyMapper;
import com.heu.finance.pojo.finance.PayMoney;
import com.heu.finance.service.admin.finance.PayMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayMoneyImpl implements PayMoneyService {
    @Autowired
    private PayMoneyMapper payMoneyMapper;

    @Override
    public List<PayMoney> selectPayMoneyAll() {
        return payMoneyMapper.selectPayMoneyAll();
    }

    @Override
    public int addPayMoney(PayMoney payMoney) {
        return payMoneyMapper.addPayMoney(payMoney);
    }

    //修改回显

    @Override
    public PayMoney selectPayMoneyById(Integer id) {
        return payMoneyMapper.selectPayMoneyById(id);
    }

    //更新

    @Override
    public int updatePayMoney(PayMoney payMoney) {
        return payMoneyMapper.updatePayMoney(payMoney);
    }

    //删除

    @Override
    public int deletePayMoney(Integer id) {
        return payMoneyMapper.deletePayMoney(id);
    }
}
