package com.heu.finance.pojo.permission;

public class AdminPermissions {
    private Integer id;
    private Integer adminId;
    private Permission permission;

    @Override
    public String toString() {
        return "AdminPermissions{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", permission=" + permission +
                '}';
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
