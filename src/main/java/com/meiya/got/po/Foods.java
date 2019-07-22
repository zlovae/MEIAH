package com.meiya.got.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Foods implements Serializable {
    private Long id;
    private Long store_id;
    private String name;
    private String photo;
    private String photo_sub;
    private Long cate_id;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private Integer status;
    private Date creat_time;
    private Date update_time;

    @Override
    public String toString() {
        return "Foods{" +
                "id=" + id +
                ", store_id=" + store_id +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", photo_sub='" + photo_sub + '\'' +
                ", cate_id=" + cate_id +
                ", describe='" + description + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", status=" + status +
                ", creat_time=" + creat_time +
                ", update_time=" + update_time +
                '}';
    }

    public Foods(Long id, Long store_id, String name, String photo, String photo_sub, Long cate_id, String description, Integer stock, BigDecimal price, Integer status, Date creat_time, Date update_time) {
        this.id = id;
        this.store_id = store_id;
        this.name = name;
        this.photo = photo;
        this.photo_sub = photo_sub;
        this.cate_id = cate_id;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.creat_time = creat_time;
        this.update_time = update_time;
    }

    public Foods() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto_sub() {
        return photo_sub;
    }

    public void setPhoto_sub(String photo_sub) {
        this.photo_sub = photo_sub;
    }

    public Long getCate_id() {
        return cate_id;
    }

    public void setCate_id(Long cate_id) {
        this.cate_id = cate_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescribe(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
