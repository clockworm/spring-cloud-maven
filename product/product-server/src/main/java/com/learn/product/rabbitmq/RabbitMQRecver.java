package com.learn.product.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQRecver {
	
	@RabbitListener(queues = "user.login.queue")
	public void userLoginQueueListener(@Payload String message,CorrelationData correlationData) throws Exception{
		log.info("该用户完成了登录:{}",message);
	}
	
	@RabbitListener(queues = "headers.queue")
	public void headersQueueListener(@Payload byte[] message) throws Exception{
		log.info("header:{}",new String(message));
	}
	
	@RabbitListener(queues = "product.kill.queue")
	public void productKillQueueListener(@Payload String message) throws Exception{
		log.info("该用完成了秒杀:{}",message);
	}
	
}
