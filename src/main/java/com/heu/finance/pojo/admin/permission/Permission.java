package com.heu.finance.pojo.admin.permission;

public class Permission {
    private Integer id;
    private String permission;

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
