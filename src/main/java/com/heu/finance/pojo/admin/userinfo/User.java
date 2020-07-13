package com.heu.finance.pojo.admin.userinfo;

import java.security.PublicKey;

public class User {
    private Integer id;
    private String username;
    private String realname;
    private String password;
    private String IDcard;
    private String phone;
    private String email;
    private Integer paypwd;
    private Integer status;
    private String reputation;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", IDcard='" + IDcard + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", paypwd=" + paypwd +
                ", status=" + status +
                ", reputation='" + reputation + '\'' +
                '}';
    }

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPaypwd() {
        return paypwd;
    }

    public void setPaypwd(Integer paypwd) {
        this.paypwd = paypwd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }
}
