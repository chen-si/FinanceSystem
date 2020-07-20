package com.heu.finance.pojo.tools;

import java.util.Date;

public class UserApplyLoan {
    private Integer loanId;
    private Date loanTime;
    private Double amount;
    private Double rate;
    private Integer term;
    private Integer applyStatus;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Override
    public String toString() {
        return "UserApplyLoan{" +
                "loanId=" + loanId +
                ", loanTime=" + loanTime +
                ", amount=" + amount +
                ", rate=" + rate +
                ", term=" + term +
                ", applyStatus=" + applyStatus +
                '}';
    }

    public UserApplyLoan(){

    }
}
