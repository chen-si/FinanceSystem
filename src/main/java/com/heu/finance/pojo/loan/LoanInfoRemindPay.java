package com.heu.finance.pojo.loan;

public class LoanInfoRemindPay {
    private Integer loanId;
    private String username;
    private Double amount;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "LoanInfoRemindPay{" +
                "loanId=" + loanId +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                '}';
    }

    public LoanInfoRemindPay(){

    }
}
