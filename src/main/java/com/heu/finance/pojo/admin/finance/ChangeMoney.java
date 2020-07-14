package com.heu.finance.pojo.admin.finance;

public class ChangeMoney {
    private int id;
    private String name;
    private double annualIncome;
    private double peiIncome;
    private double invesMoney;
    private String invesTerm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public double getPeiIncome() {
        return peiIncome;
    }

    public void setPeiIncome(double peiIncome) {
        this.peiIncome = peiIncome;
    }

    public double getInvesMoney() {
        return invesMoney;
    }

    public void setInvesMoney(double invesMoney) {
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
