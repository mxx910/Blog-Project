package com.mxx910.blog.config;


import com.mxx910.blog.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: mxx910
 * @date: 2023/4/19
 * @description: RabbitMq配置
 */
@Slf4j
@Configuration
public class RabbitMqConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 邮件交换机
     */
    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(MqConstant.EMAIL_EXCHANGE, true, false);
    }

    /**
     * 邮件Simple队列
     */
    @Bean
    public Queue emailSimpleQueue() {
        return new Queue(MqConstant.EMAIL_SIMPLE_QUEUE, true);
    }

    /**
     * 邮件Html队列
     */
    @Bean
    public Queue emailHtmlQueue() {
        return new Queue(MqConstant.EMAIL_HTML_QUEUE, true);
    }

    /**
     * 绑定邮件Simple队列
     */
    @Bean
    public Binding simpleQueueBinding() {
        return BindingBuilder.bind(emailSimpleQueue()).to(emailExchange()).with(MqConstant.EMAIL_SIMPLE_KEY);
    }

    /**
     * 绑定邮件Html队列
     */
    @Bean
    public Binding htmlQueueBinding() {
        return BindingBuilder.bind(emailHtmlQueue()).to(emailExchange()).with(MqConstant.EMAIL_HTML_KEY);
    }

    /**
     * 文章交换机
     */
    @Bean
    public TopicExchange articleExchange() {
        return new TopicExchange(MqConstant.ARTICLE_EXCHANGE, true, false);
    }

    /**
     * 文章队列
     */
    @Bean
    public Queue articleQueue() {
        return new Queue(MqConstant.ARTICLE_QUEUE, true);
    }

    /**
     * 绑定文章队列
     */
    @Bean
    public Binding articleQueueBinding() {
        return BindingBuilder.bind(articleQueue()).to(articleExchange()).with(MqConstant.ARTICLE_KEY);
    }

}
