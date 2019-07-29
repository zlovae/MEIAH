package com.meiya.got.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SeckillVo implements Serializable {

    private Long id;
    private Long goods_id;
    private BigDecimal seckill_price;
    private Date start_date;
    private Date end_date;
    private Long sid;
    private Long events_id;
    private Integer status;
    private Integer counts;

    public SeckillVo() {
    }

    public SeckillVo(Long id, Long goods_id, BigDecimal seckill_price, Date start_date, Date end_date, Long sid, Long events_id, Integer status, Integer counts) {
        this.id = id;
        this.goods_id = goods_id;
        this.seckill_price = seckill_price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.sid = sid;
        this.events_id = events_id;
        this.status = status;
        this.counts = counts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public BigDecimal getSeckill_price() {
        return seckill_price;
    }

    public void setSeckill_price(BigDecimal seckill_price) {
        this.seckill_price = seckill_price;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getEvents_id() {
        return events_id;
    }

    public void setEvents_id(Long events_id) {
        this.events_id = events_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "SeckillVo{" +
                "id=" + id +
                ", goods_id=" + goods_id +
                ", seckill_price=" + seckill_price +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", sid=" + sid +
                ", events_id=" + events_id +
                ", status=" + status +
                ", counts=" + counts +
                '}';
    }
}
