package com.heu.finance.service.impl.normal.personal;

import com.heu.finance.mapper.normal.personal.MyInfoMapper;
import com.heu.finance.pojo.personal.MyInfo;
import com.heu.finance.pojo.personal.SendInfo;
import com.heu.finance.service.normal.personal.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyInfoImpl implements MyInfoService {

    @Autowired
    private MyInfoMapper myInfoMapper;

    @Override
    public List<MyInfo> selectMyInfo(Integer id) {
        return myInfoMapper.selectMyInfo(id);
    }

    //已读

    @Override
    public int updateInfo(Integer infoId) {
        return myInfoMapper.updateInfo(infoId);
    }

    //删除

    @Override
    public int deleteInfo(Integer infoId) {
        return myInfoMapper.deleteInfo(infoId);
    }

    //提醒还款

    @Override
    public int remindpay(SendInfo sendInfo) {
        return myInfoMapper.remindpay(sendInfo);
    }

    //审核通过通知

    @Override
    public int passExam(SendInfo sendInfo) {
        return myInfoMapper.passExam(sendInfo);
    }

    //审核未通过通知

    @Override
    public int notPassExam(SendInfo sendInfo) {
        return myInfoMapper.notPassExam(sendInfo);
    }
}
