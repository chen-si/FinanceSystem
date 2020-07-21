package com.heu.finance.service.normal.personal;

import com.heu.finance.pojo.personal.MyInfo;
import com.heu.finance.pojo.personal.SendInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyInfoService {

    public List<MyInfo> selectMyInfo(Integer id);

    //已读
    public int updateInfo(Integer infoId);

    //删除
    public int deleteInfo(Integer infoId);

    //提醒还款
    public int remindpay(SendInfo sendInfo);

    //审核通过通知
    public int passExam(SendInfo sendInfo);

    //审核未通过通知
    public  int notPassExam(SendInfo sendInfo);
}
