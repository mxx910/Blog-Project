package com.mxx910.blog;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description:
 */
//@Component
//public class Consumer {
//
//    @RabbitListener(queues = "TEXT_QUEUE")
//    public void process(Message message){
//        byte[] body = message.getBody();
//        System.out.println(new String(body));
//    }
//}
