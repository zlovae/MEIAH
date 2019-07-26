package com.meiya.got.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cart implements Serializable {
//    private Long id;
//    private Long user_id;
    private Long store_id;
    private Long product_id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total_price;
    //private Integer checked;
//    private Date create_time;
//    private Date update_time;


    public Cart(Long store_id, Long product_id, String name, BigDecimal price, Integer quantity, BigDecimal total_price) {
        this.store_id = store_id;
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total_price = total_price;
    }

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "store_id=" + store_id +
                ", product_id=" + product_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                '}';
    }

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }
}
