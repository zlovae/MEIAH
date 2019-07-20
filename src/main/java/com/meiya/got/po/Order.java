package com.meiya.got.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zlovae on 2019/7/8
 * */
public class Order implements Serializable {
    private Long id;

    private Long store_id;

    private Long user_id;

    private Integer shipping_id;

    private BigDecimal payment;

    private Integer payment_type;

    private String qr_code;

    private Integer postage;

    private Integer status;

    private Date payment_time;

    private Date send_time;

    private Date end_time;

    private Date close_time;

    private Date create_time;

    private Date update_time;

    public Order(Long id, Long store_id, Long user_id, Integer shipping_id, BigDecimal payment, Integer payment_type, String qr_code, Integer postage, Integer status, Date payment_time, Date send_time, Date end_time, Date close_time, Date create_time, Date update_time) {
        this.id = id;
        this.store_id = store_id;
        this.user_id = user_id;
        this.shipping_id = shipping_id;
        this.payment = payment;
        this.payment_type = payment_type;
        this.qr_code = qr_code;
        this.postage = postage;
        this.status = status;
        this.payment_time = payment_time;
        this.send_time = send_time;
        this.end_time = end_time;
        this.close_time = close_time;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Order() {
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(Integer shipping_id) {
        this.shipping_id = shipping_id;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(Integer payment_type) {
        this.payment_type = payment_type;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public Integer getPostage() {
        return postage;
    }

    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getClose_time() {
        return close_time;
    }

    public void setClose_time(Date close_time) {
        this.close_time = close_time;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", store_id=" + store_id +
                ", user_id=" + user_id +
                ", shipping_id=" + shipping_id +
                ", payment=" + payment +
                ", payment_type=" + payment_type +
                ", qr_code='" + qr_code + '\'' +
                ", postage=" + postage +
                ", status=" + status +
                ", payment_time=" + payment_time +
                ", send_time=" + send_time +
                ", end_time=" + end_time +
                ", close_time=" + close_time +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
