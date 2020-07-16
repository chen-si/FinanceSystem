package com.heu.finance.pojo.loan;

import java.math.BigDecimal;

public class LoanExam {
    private Integer id;
    private String username;
    private BigDecimal amount;
    private Integer term;
    private String reputation;
    private Integer applyStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Override
    public String toString() {
        return "LoanExam{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", term=" + term +
                ", reputation='" + reputation + '\'' +
                ", applyStatus=" + applyStatus +
                '}';
    }

    public LoanExam(){

    }
}
