package com.learn.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
	
	@Override  
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {  
	    ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();  
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
	    converters.add(new MappingJackson2HttpMessageConverter(objectMapper));  
	}  
}
