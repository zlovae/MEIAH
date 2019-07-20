package com.meiya.got.po;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    private Long id;
    private Long user_id;
    private Long product_id;
    private Integer quantity;
    private Integer checked;
    private Date create_time;
    private Date update_time;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", checked=" + checked +
                ", creat_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public Cart(Long id, Long user_id, Long product_id, Integer quantity, Integer checked, Date creat_time, Date update_time) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.checked = checked;
        this.create_time = creat_time;
        this.update_time = update_time;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCheck() {
        return checked;
    }

    public void setCheck(Integer checked) {
        this.checked = checked;
    }

    public Date getCreat_time() {
        return create_time;
    }

    public void setCreat_time(Date creat_time) {
        this.create_time = creat_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
