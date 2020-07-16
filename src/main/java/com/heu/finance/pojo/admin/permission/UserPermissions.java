package com.heu.finance.pojo.admin.permission;

public class UserPermissions {
    private Integer id;
    private Integer userId;
    private Permission permission;

    @Override
    public String toString() {
        return "UserPermissions{" +
                "id=" + id +
                ", userId=" + userId +
                ", permission=" + permission +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
