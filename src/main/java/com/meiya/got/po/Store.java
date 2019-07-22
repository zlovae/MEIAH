package com.meiya.got.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "my_store")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Store implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String storename;
    private String photo_url;
    private String phone;
    private String address;
    private String password;
    private String salt;
    private int status;
    private String describe;
    private String update_ip;
    private Date create_time;
    private Date update_time;
    private String star;


    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + storename + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", describe='" + describe + '\'' +
                ", update_ip='" + update_ip + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", status=" + status +
                '}';
    }

    public Store(int id, String storename, String photo_url, String phone, String address, Long cate_id, String password, String salt, String describe, String update_ip, Date create_time, Date update_time, int status) {
        this.id = id;
        this.storename = storename;
        this.photo_url = photo_url;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.salt = salt;
        this.describe = describe;
        this.update_ip = update_ip;
        this.create_time = create_time;
        this.update_time = update_time;
        this.status = status;
    }

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String name) {
        this.storename = name;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUpdate_ip() {
        return update_ip;
    }

    public void setUpdate_ip(String update_ip) {
        this.update_ip = update_ip;
    }

    public Date getcreate_time() {
        return create_time;
    }

    public void setcreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
