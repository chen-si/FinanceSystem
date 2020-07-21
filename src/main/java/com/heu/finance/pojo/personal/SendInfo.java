package com.heu.finance.pojo.personal;

import java.util.Date;

public class SendInfo {
    private Integer receiveId;
    private Date createTime;
    private String title;
    private String username;
    private Double amount;

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "SendInfo{" +
                "receiveId=" + receiveId +
                ", createTime=" + createTime +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                '}';
    }

    public SendInfo(){

    }
}
