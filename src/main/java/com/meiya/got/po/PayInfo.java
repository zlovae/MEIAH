package com.meiya.got.po;

import java.io.Serializable;
import java.util.Date;

public class PayInfo implements Serializable {
    private Long id;
    private Long user_id;
    private Long store_id;
    private Long order_id;
    private Long shipping_id;
    private Double shipping_price;
    private Double payment;
    private Integer platform;
    private Integer status;
    private Long serial_number;
    private Date creat_time;
    private Date update_time;


    @Override
    public String toString() {
        return "PayInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", store_id=" + store_id +
                ", order_id=" + order_id +
                ", shipping_id=" + shipping_id +
                ", shipping_price=" + shipping_price +
                ", payment=" + payment +
                ", platform=" + platform +
                ", status=" + status +
                ", serial_number=" + serial_number +
                ", creat_time=" + creat_time +
                ", update_time=" + update_time +
                '}';
    }

    public PayInfo(Long id, Long user_id, Long store_id, Long order_id, Long shipping_id, Double shipping_price, Double payment, Integer platform, Integer status, Long serial_number, Date creat_time, Date update_time) {
        this.id = id;
        this.user_id = user_id;
        this.store_id = store_id;
        this.order_id = order_id;
        this.shipping_id = shipping_id;
        this.shipping_price = shipping_price;
        this.payment = payment;
        this.platform = platform;
        this.status = status;
        this.serial_number = serial_number;
        this.creat_time = creat_time;
        this.update_time = update_time;
    }

    public PayInfo() {
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

    public Long getStore_id() {
        return store_id;
    }

    public void setStore_id(Long store_id) {
        this.store_id = store_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(Long shipping_id) {
        this.shipping_id = shipping_id;
    }

    public Double getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(Double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(Long serial_number) {
        this.serial_number = serial_number;
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
