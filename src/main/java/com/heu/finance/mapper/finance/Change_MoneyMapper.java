package com.heu.finance.mapper.finance;

import com.heu.finance.pojo.finance.ChangeMoney;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
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

    //排序功能
    List<ChangeMoney> selectChangeMoneyOrderBy(String orderBy);
}
