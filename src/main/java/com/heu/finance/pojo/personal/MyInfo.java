package com.heu.finance.pojo.personal;

import java.util.Date;

public class MyInfo {
    private Integer id;
    private String title;
    private String infoDesc;
    private String username;
    private Date createTime;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", infoDesc='" + infoDesc + '\'' +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    public MyInfo(){

    }
}
