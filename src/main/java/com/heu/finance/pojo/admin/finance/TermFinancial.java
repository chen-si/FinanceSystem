package com.heu.finance.pojo.admin.finance;

public class TermFinancial {
    private Integer id;
    private String name;
    private String invesTerm;
    private Double leastMoney;
    private Integer profit;
    private Double annualIncome;

    @Override
    public String toString() {
        return "TermFinancial{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", invesTerm='" + invesTerm + '\'' +
                ", leastMoney=" + leastMoney +
                ", profit=" + profit +
                ", annualIncome=" + annualIncome +
                '}';
    }

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

    public String getInvesTerm() {
        return invesTerm;
    }

    public void setInvesTerm(String invesTerm) {
        this.invesTerm = invesTerm;
    }

    public Double getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(Double leastMoney) {
        this.leastMoney = leastMoney;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }
}
