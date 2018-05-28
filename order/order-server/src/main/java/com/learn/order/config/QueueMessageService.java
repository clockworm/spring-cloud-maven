package com.learn.order.config;


import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author TangLingYun
 * @detail 消息队列业务
 */
public interface QueueMessageService extends RabbitTemplate.ConfirmCallback {
	
	
	/**
     * 发送消息到RabbitMQ消息队列
     * @param message 消息内容
     * @param RabbitRoutingEnum MQ路由配置枚举
     * @throws Exception
     */
    public void send(Object message, RabbitRoutingEnum routingEnum) throws Exception;
    
	/**
     * 发送消息到RabbitMQ消息队列
     * @param properties 头信息 
     * @param message 消息内容
     * @param RabbitRoutingEnum MQ路由配置枚举
     * @throws Exception
     */
    public void send(Object message, MessageProperties properties,RabbitRoutingEnum routingEnum) throws Exception;
}

