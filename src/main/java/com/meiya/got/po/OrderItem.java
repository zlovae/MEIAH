package com.meiya.got.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderItem implements Serializable {
    private Long id;
    private String name;
    private Long order_id;
    private Long food_id;
    private String photo;
    private BigDecimal price;
    private Integer quantity ;
    private Long discount_id;
    private BigDecimal total_price;

    public OrderItem(Long id, String name, Long order_id, Long food_id, String photo, BigDecimal price, Integer quantity, Long discount_id, BigDecimal total_price) {
        this.id = id;
        this.name = name;
        this.order_id = order_id;
        this.food_id = food_id;
        this.photo = photo;
        this.price = price;
        this.quantity = quantity;
        this.discount_id = discount_id;
        this.total_price = total_price;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getProduct_id() {
        return food_id;
    }

    public void setProduct_id(Long product_id) {
        this.food_id = product_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Long discount_id) {
        this.discount_id = discount_id;
    }

    public BigDecimal getTotal() {
        return total_price;
    }

    public void setTotal(BigDecimal total) {
        this.total_price = total;
    }

}
