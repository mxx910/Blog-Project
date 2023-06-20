package com.mxx910.blog.consumer;

import com.mxx910.blog.constant.MqConstant;
import com.mxx910.blog.model.DTO.MailDTO;
import com.mxx910.blog.service.EmailService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author: mxx910
 * @date: 2023/4/22
 * @description:
 */
@Component
public class EmailConsumer {
    @Autowired
    private EmailService emailService;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = MqConstant.EMAIL_SIMPLE_QUEUE, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstant.EMAIL_EXCHANGE, type = ExchangeTypes.TOPIC),
                    key =MqConstant. EMAIL_SIMPLE_KEY
            )})
    public void listenSendSimpleEmail(@Payload MailDTO mail) {
        emailService.sendSimpleMail(mail);
    }
}
