package com.meiya.got.receiver;

import com.meiya.got.po.MsgConnection;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    @RabbitListener(queues = "user.queue")
    public void consumeMessage(MsgConnection msgConnection) {
        System.out.println(msgConnection.toString());
    }

}
