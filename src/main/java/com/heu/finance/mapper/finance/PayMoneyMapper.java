package com.heu.finance.mapper.finance;

import com.heu.finance.pojo.finance.PayMoney;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
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

    //排序
    public List<PayMoney> selectPayMoneyOrderBy(String orderBy);
}
