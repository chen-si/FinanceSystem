package com.heu.finance.pojo.finance;

import java.math.BigDecimal;
import java.util.Date;

public class UserPayMoney {
    private Integer id;
    private Integer userId;
    private Integer payId;
    private Date startTime;
    private BigDecimal averYield;
    private BigDecimal profit;
    private Integer status;
    private Integer type;
    private BigDecimal monthMoney;
    private String invesTerm;

    public BigDecimal getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(BigDecimal monthMoney) {
        this.monthMoney = monthMoney;
    }

    public String getInvesTerm() {
        return invesTerm;
    }

    public void setInvesTerm(String invesTerm) {
        this.invesTerm = invesTerm;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
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
        return "UserPayMoney{" +
                "id=" + id +
                ", userId=" + userId +
                ", payId=" + payId +
                ", startTime=" + startTime +
                ", averYield=" + averYield +
                ", profit=" + profit +
                ", status=" + status +
                ", type=" + type +
                ", monthMoney=" + monthMoney +
                ", invesTerm='" + invesTerm + '\'' +
                '}';
    }

    public UserPayMoney(){

    }
}
