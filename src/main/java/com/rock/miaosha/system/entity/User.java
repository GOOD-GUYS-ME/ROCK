package com.rock.miaosha.system.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@MappedSuperclass
public abstract class User implements Serializable {
    private static final long serialVersionUID = 599176259373172465L;
    @Column
    private String userName;

    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String salt;
    @Column
    private int sex;
    @Column
    private int age;
    @Column
    private String address;
    @Column
    private int state;
    @Column
    @CreationTimestamp
    private Date createTime;
    @Column
    @UpdateTimestamp
    private Date last_update;//最新修改时间；

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                ", last_update=" + last_update +
                '}';
    }
}
