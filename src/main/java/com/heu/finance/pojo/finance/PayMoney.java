package com.heu.finance.pojo.finance;

import java.math.BigDecimal;

public class PayMoney {
    private Integer id;
    private Integer type;
    private BigDecimal monthMoney;
    private Integer autoInto;
    private String invesTerm;

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

    public BigDecimal getMonthMoney() {
        return monthMoney;
    }

    public void setMonthMoney(BigDecimal monthMoney) {
        this.monthMoney = monthMoney;
    }

    public Integer getAutoInto() {
        return autoInto;
    }

    public void setAutoInto(Integer autoInto) {
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
