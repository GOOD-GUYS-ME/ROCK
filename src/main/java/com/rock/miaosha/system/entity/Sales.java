package com.rock.miaosha.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sales extends User implements Serializable {


    private static final long serialVersionUID = 7908509270412117817L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Goods> goods;

    @ManyToOne
    private Role role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", goods=" + goods +
                ", role=" + role +
                '}';
    }
}
