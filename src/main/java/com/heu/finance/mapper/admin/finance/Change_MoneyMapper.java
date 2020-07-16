package com.heu.finance.mapper.admin.finance;

import com.heu.finance.pojo.finance.ChangeMoney;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Change_MoneyMapper {


    public List<ChangeMoney> selectChangeMoneyAll();

    //新增
    public int addChangeMoney(ChangeMoney changeMoney);

    //按id查询
    public ChangeMoney selectChangeMoneyById(Integer id);

    //修改
    public int alterChangeMoney(ChangeMoney changeMoney);

    //删除
    public int deleteChangeMoney(Integer id);

}
