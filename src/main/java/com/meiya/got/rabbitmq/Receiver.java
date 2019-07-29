package com.meiya.got.rabbitmq;

import com.meiya.got.po.MsgConnection;
import com.meiya.got.service.impl.OrderServiceImpl;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "seckill.queue")
public class Receiver {

    @Autowired
    private OrderServiceImpl orderService;

    @RabbitHandler
    public void receiver(@Payload SeckillMessage seckillMessage, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        System.out.println(seckillMessage);
        Long userId = seckillMessage.getUserId();
        Long foodId = seckillMessage.getFoodId();
        //Long user_id = msgConnection.getUser_id();
        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            //orderService.seckill()
            channel.basicAck(deliverTag, false);//成功消费
        } catch (Exception e) {
            channel.basicNack(deliverTag, false, true);//返回队列
        }
    }
}