package com.meiya.got.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "my_user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private Integer sex;
    private Date birth;
    private String photo_url;
    private String phone;
    private String address;
    private String password;
    private String salt;
    private Integer role;
    private Integer status;
    private String mark;
    private String update_ip;
    private Date create_time;
    private Date update_time;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", photo_url='" + photo_url + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", mark='" + mark + '\'' +
                ", update_ip='" + update_ip + '\'' +
                ", creat_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }

    public User(Long id, String name, Integer sex, Date birth, String photo_url, String phone, String address, String password, String salt, Integer role, Integer status, String mark, String update_ip, Date create_time, Date update_time) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birth = birth;
        this.photo_url = photo_url;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.salt = salt;
        this.role = role;
        this.status = status;
        this.mark = mark;
        this.update_ip = update_ip;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public User() {
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUpdate_ip() {
        return update_ip;
    }

    public void setUpdate_ip(String update_ip) {
        this.update_ip = update_ip;
    }


    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
