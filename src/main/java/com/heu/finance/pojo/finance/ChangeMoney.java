package com.heu.finance.pojo.finance;

import java.math.BigDecimal;

public class ChangeMoney {
    private Integer id;
    private String name;
    private BigDecimal annualIncome;
    private BigDecimal peiIncome;
    private BigDecimal invesMoney;
    private String invesTerm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(BigDecimal annualIncome) {
        this.annualIncome = annualIncome;
    }

    public BigDecimal getPeiIncome() {
        return peiIncome;
    }

    public void setPeiIncome(BigDecimal peiIncome) {
        this.peiIncome = peiIncome;
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

    @Override
    public String toString() {
        return "ChangeMoney{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", annualIncome=" + annualIncome +
                ", peiIncome=" + peiIncome +
                ", invesMoney=" + invesMoney +
                ", invesTerm='" + invesTerm + '\'' +
                '}';
    }

    public ChangeMoney(){

    }
}
