package com.learn.order.rest.api;

import com.alibaba.druid.support.json.JSONUtils;
import com.learn.order.config.OrderCofing;
import com.learn.order.config.RabbitRoutingEnum;
import com.learn.order.converter.OrderForm2OrderDTO;
import com.learn.order.dto.OrderDTO;
import com.learn.order.enums.ResultEnum;
import com.learn.order.exception.OrderException;
import com.learn.order.message.StreeamClient;
import com.learn.order.rest.form.OrderForm;
import com.learn.order.service.OrderService;
import com.learn.product.dto.ResultDTO;
import com.learn.product.util.ResultDTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderCofing orderCofing;
	@Autowired
	private StreeamClient streeamClient;

	@PostMapping("create")
	public ResultDTO<?> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
		log.info("配置中心配置对象orderCofing={}",orderCofing);
		if (bindingResult.hasErrors()) {
			log.error("请求创建订单 参数校验错误:", orderForm);
			throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
		}
		OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
		if(CollectionUtils.isEmpty(orderDTO.getDetailList())) {
			log.error("创建订单异常,购物车为空 orderForm:{}",orderForm);
			throw new OrderException(ResultEnum.CAR_ERROR);
		}
		OrderDTO dto = orderService.create(orderDTO);
		Map<String,String> map = new HashMap<>();
		map.put("orderId", dto.getOrderId());
		try {
//			String jsonString = JSONUtils.toJSONString(dto);
			streeamClient.output().send(MessageBuilder.withPayload(dto).build());
		} catch (Exception e) {
			log.error("发送MQ秒杀下单异常:{}",e);
		}
		return ResultDTOUtil.success(map);
	}
	
	@PostMapping("refushConfig")
	public void refushConfig(){
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String,String> map = new HashMap<>();
		ResponseEntity<String> entity = restTemplate.postForEntity("http://118.24.49.184:7171/actuator/bus-refresh",map,String.class);
		System.err.println(entity.getBody());
	}
}
