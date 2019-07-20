package com.meiya.got.vo;

import java.io.Serializable;

public class StoreVo implements Serializable {
    private Long id;
    private String name;
    private String photo_url;
    private String phone;
    private String address;
    private Long cate_id;
    private String description;
    private Integer status;

    public StoreVo(Long id, String name, String photo_url, String phone, String address, Long cate_id, String description, Integer status) {
        this.id = id;
        this.name = name;
        this.photo_url = photo_url;
        this.phone = phone;
        this.address = address;
        this.cate_id = cate_id;
        this.description = description;
        this.status = status;
    }

    public StoreVo() {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
