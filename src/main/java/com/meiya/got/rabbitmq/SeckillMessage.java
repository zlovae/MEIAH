package com.meiya.got.rabbitmq;

import java.io.Serializable;

public class SeckillMessage implements Serializable {

    Long userId;
    Long foodId;

    public SeckillMessage() {
    }

    public SeckillMessage(Long userId, Long foodId) {
        this.userId = userId;
        this.foodId = foodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    @Override
    public String toString() {
        return "SeckillMessage{" +
                "userId=" + userId +
                ", foodId=" + foodId +
                '}';
    }
}
