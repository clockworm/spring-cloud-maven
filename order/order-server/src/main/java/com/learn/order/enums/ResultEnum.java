package com.learn.order.enums;

import com.learn.product.enums.CodeEnum;

import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {
	CAR_ERROR(001,"购物车没有商品"),
	PARAM_ERROR(101, "參數不正確"),
	SUCCESS(0, "成功"), 
	LOGIN_FAIL(102, "密码或用户名不正确"),
	USER_DISABLE(103, "账户冻结,请联系管理员"),
	USER_NOT_REGISTER(104, "用户未注册"),
	ERROR(777,"未知错误"),
	REQUEST_ERROR(444,"非法请求"),
	ACCESS_LIMIT(445,"请求过于频繁"),
	NOT_AUTHORIZATION(0101,"权限不足"),
	SERVER_ERROR(411,"服务端异常,请稍后重试"),
	CRUD_FAIL(1009,"操作失败,请稍后重试"),
	PRODUCT_NOT_EXIST(5005,"商品不存在"), 
	PRODUCT_STOCK_ERROR(5006,"扣库存错误");
	/** 返回编码*/
	private Integer code;
	/** 返回信息*/
	private String message;

	ResultEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
