package com.coviam.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by coviam on 29/07/17.
 */
@Entity
@Table(name="orderTable")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="order_id")
    private Integer orderId;

    @Column(nullable = false)
    private String userEmail;

    @OneToMany(cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
    private Set<Item> items;

    public Set<Item> getItems() {
        return items;
    }
    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userEmail='" + userEmail + '\'' +
                ", items=" + items +
                '}';
    }
}