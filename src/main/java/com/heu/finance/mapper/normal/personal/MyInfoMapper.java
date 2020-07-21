package com.heu.finance.mapper.normal.personal;

import com.heu.finance.pojo.personal.MyInfo;
import com.heu.finance.pojo.personal.SendInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyInfoMapper {

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
