package com.meiya.got.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "my_foods")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long sid;
    private String name;
    private String photo_url;
    private String photo_sub;
    private int cate_id;
    private String describe;
    private Integer stock;
    private BigDecimal price;
    private Integer status;
    private Date create_time;
    private Date update_time;

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", sid=" + sid +
                ", name='" + name + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", photo_sub='" + photo_sub + '\'' +
                ", cate_id=" + cate_id +
                ", describe='" + describe + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", status=" + status +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public Food(Long id, Long sid, String name, String photo_url, String photo_sub, int cate_id, String describe, Integer stock, BigDecimal price, Integer status, Date create_time, Date update_time) {
        this.id = id;
        this.sid = sid;
        this.name = name;
        this.photo_url = photo_url;
        this.photo_sub = photo_sub;
        this.cate_id = cate_id;
        this.describe = describe;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Food() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getphoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getPhoto_sub() {
        return photo_sub;
    }

    public void setPhoto_sub(String photo_sub) {
        this.photo_sub = photo_sub;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
