package com.heu.finance.pojo.admin.finance;

public class PayMoney {
    private int id;
    private int type;
    private double monthMoney;
    private int autoInto;
    private String invesTerm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(double monthMoney) {
        this.monthMoney = monthMoney;
    }

    public int getAutoInto() {
        return autoInto;
    }

    public void setAutoInto(int autoInto) {
        this.autoInto = autoInto;
    }

    public String getInvesTerm() {
        return invesTerm;
    }

    public void setInvesTerm(String invesTerm) {
        this.invesTerm = invesTerm;
    }

    @Override
    public String toString() {
        return "PayMoney{" +
                "id=" + id +
                ", type=" + type +
                ", monthMoney=" + monthMoney +
                ", autoInto=" + autoInto +
                ", invesTerm='" + invesTerm + '\'' +
                '}';
    }

    public PayMoney(){

    }
}
