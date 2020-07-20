package com.heu.finance.pojo.finance;

import java.math.BigDecimal;
import java.util.Date;

public class UserChangeMoney {
    private Integer id;
    private Integer userId;
    private Integer changeId;
    private Date startTime;
    private BigDecimal averYield;
    private BigDecimal profit;
    private Integer status;
    private String changeMoneyName;
    private BigDecimal invesMoney;
    private String invesTerm;

    public String getChangeMoneyName() {
        return changeMoneyName;
    }

    public void setChangeMoneyName(String changeMoneyName) {
        this.changeMoneyName = changeMoneyName;
    }

    public BigDecimal getInvesMoney() {
        return invesMoney;
    }

    public void setInvesMoney(BigDecimal invesMoney) {
        this.invesMoney = invesMoney;
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

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
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

    @Override
    public String toString() {
        return "UserChangeMoney{" +
                "id=" + id +
                ", userId=" + userId +
                ", changeId=" + changeId +
                ", startTime=" + startTime +
                ", averYield=" + averYield +
                ", profit=" + profit +
                ", status=" + status +
                ", changeMoneyName='" + changeMoneyName + '\'' +
                ", invesMoney=" + invesMoney +
                ", invesTerm='" + invesTerm + '\'' +
                '}';
    }

    public UserChangeMoney(){

    }
}
