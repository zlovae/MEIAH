package com.meiya.got.vo;

import net.bytebuddy.implementation.bytecode.assign.TypeCasting;

import java.io.Serializable;
import java.util.Date;

public class EventsVo implements Serializable {

    private Long id;
    private String name;
    private Date start_time;
    private Date end_time;
    private String description;
    private String photo_url;

    public EventsVo() {
    }

    public EventsVo(Long id, String name, Date start_time, Date end_time, String description, String photo_url) {
        this.id = id;
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.photo_url = photo_url;
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

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public String toString() {
        return "EventsVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", description='" + description + '\'' +
                ", photo_url='" + photo_url + '\'' +
                '}';
    }
}
