package com.heu.finance.pojo.loan;

import java.math.BigDecimal;

public class LoanInfo {
    private Integer id;
    private String username;
    private String IDcard;
    private String phone;
    private BigDecimal amount;
    private Integer term;
    private Integer loanStatus;

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

    public String getIDcard() {
        return IDcard;
    }

    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "LoanInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", IDcard='" + IDcard + '\'' +
                ", phone='" + phone + '\'' +
                ", amount=" + amount +
                ", term=" + term +
                ", loanStatus=" + loanStatus +
                '}';
    }

    public LoanInfo(){

    }
}
