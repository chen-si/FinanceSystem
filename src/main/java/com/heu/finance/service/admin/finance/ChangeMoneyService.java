package com.heu.finance.service.admin.finance;


import com.heu.finance.pojo.finance.ChangeMoney;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ChangeMoneyService {
    public List<ChangeMoney> selectChangeMoneyAll();

    //新增
    public int addChangeMoney(ChangeMoney changeMoney);

    //按id查询
    public ChangeMoney selectChangeMoneyById(Integer id);

    //修改
    public int alterChangeMoney(ChangeMoney changeMoney);

    //删除
    public int deleteChangeMoney(Integer id);

    List<ChangeMoney> selectChangeMoneyOrderBy(String orderBy);
}
