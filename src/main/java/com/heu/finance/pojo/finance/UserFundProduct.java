package com.heu.finance.pojo.finance;

import java.math.BigDecimal;
import java.util.Date;

public class UserFundProduct {
    private Integer id;
    private Integer userId;
    private Integer fundId;
    private String startTime;
    private BigDecimal averYield;
    private BigDecimal profit;
    private Integer status;

    @Override
    public String toString() {
        return "UserFundProduct{" +
                "id=" + id +
                ", userId=" + userId +
                ", fundId=" + fundId +
                ", startTime=" + startTime +
                ", averYield=" + averYield +
                ", profit=" + profit +
                ", status=" + status +
                '}';
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
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
