package com.meiya.got.controller;

import com.meiya.got.common.ServerResponse;
import com.meiya.got.po.MsgConnection;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/")
@RabbitListener(queues = "store.queue")
public class ReceiverController {

    private static Map<Long, Integer> userMap = new HashMap<>();

    @RequestMapping("get")
    public ServerResponse MessageController(@RequestParam("uid")Long uid) {
        if(!userMap.containsKey(uid)) {
            userMap.put(uid, 0);
        }
        switch (userMap.get(uid)) {
            case 0 :
                return null;
            case 1 :
                return ServerResponse.createBySuccessMessage("卖家拒单了");
            case 2 :
                return ServerResponse.createBySuccessMessage("卖家已发货");
            case 3 :
                return ServerResponse.createBySuccessMessage("卖家已退款");
        }
        return ServerResponse.createByError();
    }

    @RequestMapping("shut")
    public void ShutdownController(@RequestParam("uid")Long uid) {
        if(userMap.containsKey(uid)) {
            userMap.remove(uid);
        }
    }

    @RabbitHandler
    public void receiver(@Payload MsgConnection msgConnection, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        Long user_id = msgConnection.getUser_id();
        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        if(!userMap.containsKey(user_id)) {
            channel.basicNack(deliverTag, false, true);
            return;
        }

        channel.basicAck(deliverTag, false);

        switch (msgConnection.getStore_mid()) {
            case 1 : userMap.put(user_id, 1); break;
            case 2 : userMap.put(user_id, 2); break;
            case 3 : userMap.put(user_id, 3); break;
            default: break;
        }
    }
}
