package com.rock.miaosha.system.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = -5161791191209941516L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String permissionName;
    @Column
    private String describes;
    @Column
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", describes='" + describes + '\'' +
                ", state=" + state +
                '}';
    }
}
