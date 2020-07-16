package com.heu.finance.pojo.finance;

import java.math.BigDecimal;

public class FundProduct {
    private Integer id;
    private Integer type;
    private Integer code;
    private String fundDesc;
    private BigDecimal dailyGrowth;
    private BigDecimal monthlyGrowth;
    private BigDecimal annualGrowth;
    private BigDecimal leastMoney;
    private String invesTerm;
    
    @Override
    public String toString() {
        return "FundProduct{" +
                "id=" + id +
                ", type=" + type +
                ", code=" + code +
                ", fundDesc='" + fundDesc + '\'' +
                ", dailyGrowth=" + dailyGrowth +
                ", monthlyGrowth=" + monthlyGrowth +
                ", annualGrowth=" + annualGrowth +
                ", leastMoney=" + leastMoney +
                ", invesTerm=" + invesTerm +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFundDesc() {
        return fundDesc;
    }

    public void setFundDesc(String fundDesc) {
        this.fundDesc = fundDesc;
    }

    public BigDecimal getDailyGrowth() {
        return dailyGrowth;
    }

    public void setDailyGrowth(BigDecimal dailyGrowth) {
        this.dailyGrowth = dailyGrowth;
    }

    public BigDecimal getMonthlyGrowth() {
        return monthlyGrowth;
    }

    public void setMonthlyGrowth(BigDecimal monthlyGrowth) {
        this.monthlyGrowth = monthlyGrowth;
    }

    public BigDecimal getAnnualGrowth() {
        return annualGrowth;
    }

    public void setAnnualGrowth(BigDecimal annualGrowth) {
        this.annualGrowth = annualGrowth;
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
}
