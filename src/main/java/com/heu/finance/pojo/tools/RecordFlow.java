package com.heu.finance.pojo.tools;

import java.math.BigDecimal;
import java.util.Date;

public class RecordFlow {
    private Integer id;
    private Integer userId;
    private BigDecimal flowMoney;
    private Integer type;
    private String source;
    private Date createTime;
    private String fundDesc;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getFlowMoney() {
        return flowMoney;
    }

    public void setFlowMoney(BigDecimal flowMoney) {
        this.flowMoney = flowMoney;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFundDesc() {
        return fundDesc;
    }

    public void setFundDesc(String fundDesc) {
        this.fundDesc = fundDesc;
    }

    @Override
    public String toString() {
        return "RecordFlow{" +
                "id=" + id +
                ", userId=" + userId +
                ", flowMoney=" + flowMoney +
                ", type=" + type +
                ", source='" + source + '\'' +
                ", createTime=" + createTime +
                ", fundDesc='" + fundDesc + '\'' +
                '}';
    }

    public RecordFlow(){

    }
}
