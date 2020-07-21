package com.heu.finance.pojo.finance;

import java.math.BigDecimal;
import java.util.Date;

public class UserTermFinancial {
    private Integer id;
    private Integer userId;
    private Integer termId;
    private String name;
    private BigDecimal leastMoney;
    private Date startTime;
    private String invesTerm;
    private BigDecimal profit;
    private BigDecimal averYield;
    private Integer  status;

    @Override
    public String toString() {
        return "UserTermFinancial{" +
                "id=" + id +
                ", userId=" + userId +
                ", termId=" + termId +
                ", name='" + name + '\'' +
                ", leastMoney=" + leastMoney +
                ", startTime='" + startTime + '\'' +
                ", invesTerm='" + invesTerm + '\'' +
                ", profit=" + profit +
                ", averYield=" + averYield +
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

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(BigDecimal leastMoney) {
        this.leastMoney = leastMoney;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getInvesTerm() {
        return invesTerm;
    }

    public void setInvesTerm(String invesTerm) {
        this.invesTerm = invesTerm;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getAverYield() {
        return averYield;
    }

    public void setAverYield(BigDecimal averYield) {
        this.averYield = averYield;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserTermFinancial() {
    }
}
