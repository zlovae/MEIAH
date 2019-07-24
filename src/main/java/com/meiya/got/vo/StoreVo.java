package com.meiya.got.vo;

import java.io.Serializable;

public class StoreVo implements Serializable {
    Long id;
    String storename;
    String photo_url;
    String address;
    String description;
    String star;
    Integer category_id;

    public StoreVo() {
    }

    public StoreVo(Long id, String storename, String photo_url, String address, String description, String star, Integer category_id) {
        this.id = id;
        this.storename = storename;
        this.photo_url = photo_url;
        this.address = address;
        this.description = description;
        this.star = star;
        this.category_id = category_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "StoreVo{" +
                "id=" + id +
                ", storename='" + storename + '\'' +
                ", photo_url='" + photo_url + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", star='" + star + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
