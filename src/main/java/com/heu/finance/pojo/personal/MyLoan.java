package com.heu.finance.pojo.personal;

public class MyLoan {
    private Integer id;
    private String amount;
    private Integer term;
    private Double rate;
    private Integer applyStatus;
    private Integer loanStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "MyLoan{" +
                "id=" + id +
                ", amount='" + amount + '\'' +
                ", term=" + term +
                ", rate=" + rate +
                ", applyStatus=" + applyStatus +
                ", loanStatus=" + loanStatus +
                '}';
    }

    public MyLoan(){

    }
}
