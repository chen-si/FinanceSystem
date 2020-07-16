package com.heu.finance.pojo.userinfo;

public class BankCard {
    private Integer id;
    private String cardBank;
    private Integer type;
    private String cardNum;
    private User user;

    @Override
    public String toString() {
        return "BankCard{" +
                "id=" + id +
                ", cardBank='" + cardBank + '\'' +
                ", type=" + type +
                ", cardNum='" + cardNum + '\'' +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}

