package com.heu.finance.mapper.admin.finance;

import com.heu.finance.pojo.PayMoney;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMoneyMapper {

    public List<PayMoney> selectPayMoneyAll();

    //新增
    public int  addPayMoney(PayMoney payMoney);

    //修改回显，按id查询
    public PayMoney selectPayMoneyById(Integer id);

    //更新
    public int updatePayMoney(PayMoney payMoney);

    //删除
    public int deletePayMoney(Integer id);
}