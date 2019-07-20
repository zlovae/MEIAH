package com.meiya.got.po;

import java.io.Serializable;

public class Category implements Serializable {
    private Long id;
    private String name;
    private String mark;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }

    public Category(Long id, String name, String mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Category() {
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
