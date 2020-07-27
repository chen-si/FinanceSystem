package com.heu.finance.service.admin.finance;

import com.heu.finance.pojo.finance.PayMoney;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayMoneyService {

    public List<PayMoney> selectPayMoneyAll();

    //新增
    public int addPayMoney(PayMoney payMoney);

    //修改回显
    public PayMoney selectPayMoneyById(Integer id);

    //更新
    public int updatePayMoney(PayMoney payMoney);

    //删除
    public int deletePayMoney(Integer id);

    //排序
    public List<PayMoney> selectPayMoneyOrderBy(String orderBy);
}
