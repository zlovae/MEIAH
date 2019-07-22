package com.meiya.got.po;

import java.io.Serializable;

public class Comment implements Serializable {
    private Long id;
    private Long entity_id;
    private Long user_id;
    private Integer score;
    private String comment;
    private String creat_time;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", entity_id=" + entity_id +
                ", user_id=" + user_id +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", creat_time='" + creat_time + '\'' +
                '}';
    }

    public Comment(Long id, Long entity_id, Long user_id, Integer score, String comment, String creat_time) {
        this.id = id;
        this.entity_id = entity_id;
        this.user_id = user_id;
        this.score = score;
        this.comment = comment;
        this.creat_time = creat_time;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(Long entity_id) {
        this.entity_id = entity_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }
}
