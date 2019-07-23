package com.meiya.got.sender;

import com.meiya.got.config.RabbitmqConfig;
import com.meiya.got.po.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FanoutSender {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.FANOUT_EXCHANGE, "", order);
    }
}
