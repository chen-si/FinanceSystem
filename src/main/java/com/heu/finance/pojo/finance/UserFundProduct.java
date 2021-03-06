package com.heu.finance.pojo.finance;

import java.math.BigDecimal;
import java.util.Date;

public class UserFundProduct {
    private Integer id;
    private Integer userId;
    private Integer fundId;
    private Date startTime;
    private BigDecimal averYield;
    private BigDecimal profit;
    private Integer status;
    private String fundDesc;
    private BigDecimal leastMoney;
    private String invesTerm;

    @Override
    public String toString() {
        return "UserFundProduct{" +
                "id=" + id +
                ", userId=" + userId +
                ", fundId=" + fundId +
                ", startTime='" + startTime + '\'' +
                ", averYield=" + averYield +
                ", profit=" + profit +
                ", status=" + status +
                ", fundProductDesc='" + fundDesc + '\'' +
                ", leastMoney=" + leastMoney +
                ", invesTerm='" + invesTerm + '\'' +
                '}';
    }

    public String getFundDesc() {
        return fundDesc;
    }

    public void setFundDesc(String fundDesc) {
        this.fundDesc = fundDesc;
    }

    public BigDecimal getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(BigDecimal leastMoney) {
        this.leastMoney = leastMoney;
    }

    public String getInvesTerm() {
        return invesTerm;
    }

    public void setInvesTerm(String invesTerm) {
        this.invesTerm = invesTerm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getAverYield() {
        return averYield;
    }

    public void setAverYield(BigDecimal averYield) {
        this.averYield = averYield;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
