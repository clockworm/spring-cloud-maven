package com.learn.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@RefreshScope
public class OrderCofing {
	@Value("${orderConfig.filepath}")  
	private String filepath;
	@Value("${orderConfig.openid}")
	private String openid;
	@Value("${orderConfig.phone}")
	private String phone;
}
