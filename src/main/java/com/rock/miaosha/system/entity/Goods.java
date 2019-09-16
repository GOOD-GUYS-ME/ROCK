package com.rock.miaosha.system.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = 5199988478817726419L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String goodsName;
    @Column
    private int amount;
    @Column
    private double price;
    @Column
    private int state;

    @Column
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", state=" + state +
                ", size='" + size + '\'' +
                '}';
    }
}
