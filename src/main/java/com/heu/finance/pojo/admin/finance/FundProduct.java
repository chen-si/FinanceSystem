package com.heu.finance.pojo.admin.finance;

public class FundProduct {
    private Integer id;
    private Integer type;
    private Integer code;
    private String fundDesc;
    private Double dailyGrowth;
    private Double monthlyGrowth;
    private Double annualGrowth;
    private Double leastMoney;
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

    public Double getDailyGrowth() {
        return dailyGrowth;
    }

    public void setDailyGrowth(Double dailyGrowth) {
        this.dailyGrowth = dailyGrowth;
    }

    public Double getMonthlyGrowth() {
        return monthlyGrowth;
    }

    public void setMonthlyGrowth(Double monthlyGrowth) {
        this.monthlyGrowth = monthlyGrowth;
    }

    public Double getAnnualGrowth() {
        return annualGrowth;
    }

    public void setAnnualGrowth(Double annualGrowth) {
        this.annualGrowth = annualGrowth;
    }

    public Double getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(Double leastMoney) {
        this.leastMoney = leastMoney;
    }

    public String getInvesTerm() {
        return invesTerm;
    }

    public void setInvesTerm(String invesTerm) {
        this.invesTerm = invesTerm;
    }
}
