package com.rock.miaosha.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "consumer")
public class Consumer extends User implements Serializable {
    private static final long serialVersionUID = -5254453825834795714L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Order> orders;

    @ManyToOne
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", orders=" + orders +
                ", role=" + role +
                '}';
    }
}
