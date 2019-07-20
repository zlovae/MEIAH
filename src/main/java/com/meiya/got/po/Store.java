package com.meiya.got.po;

import java.io.Serializable;
import java.util.Date;

public class Store implements Serializable {
    private Long id;
    private String name;
    private String photo_url;
    private String phone;
    private String address;
    private Long cate_id;
    private String password;
    private String salt;
    private String description;
    private String update_ip;
    private Date creat_time;
    private Date update_time;
    private int status;

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", cate_id=" + cate_id +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", description='" + description + '\'' +
                ", update_ip='" + update_ip + '\'' +
                ", creat_time=" + creat_time +
                ", update_time=" + update_time +
                ", status=" + status +
                '}';
    }

    public Store(Long id, String name, String photo_url, String phone, String address, Long cate_id, String password, String salt, String description, String update_ip, Date creat_time, Date update_time, int status) {
        this.id = id;
        this.name = name;
        this.photo_url = photo_url;
        this.phone = phone;
        this.address = address;
        this.cate_id = cate_id;
        this.password = password;
        this.salt = salt;
        this.description = description;
        this.update_ip = update_ip;
        this.creat_time = creat_time;
        this.update_time = update_time;
        this.status = status;
    }

    public Store() {
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

    public Long getCate_id() {
        return cate_id;
    }

    public void setCate_id(Long cate_id) {
        this.cate_id = cate_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return description;
    }

    public void setDescribe(String describe) {
        this.description = describe;
    }

    public String getUpdate_ip() {
        return update_ip;
    }

    public void setUpdate_ip(String update_ip) {
        this.update_ip = update_ip;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
