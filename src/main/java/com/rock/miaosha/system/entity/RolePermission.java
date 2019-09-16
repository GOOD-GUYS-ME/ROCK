package com.rock.miaosha.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//@Entity
//@Table(name = "role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 452242479129720905L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String rolePermissionName;
    @Column
    private String describe;
    @Column
    private int state;

    @OneToMany
    private List<Permission> permissions;
    @OneToMany
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolePermissionName() {
        return rolePermissionName;
    }

    public void setRolePermissionName(String rolePermissionName) {
        this.rolePermissionName = rolePermissionName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", rolePermissionName='" + rolePermissionName + '\'' +
                ", describe='" + describe + '\'' +
                ", state=" + state +
                ", permissions=" + permissions +
                ", roles=" + roles +
                '}';
    }
}
