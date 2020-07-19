package com.drew.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.drew.model.Orders;
import com.drew.service.OrdersService;


@RestController
public class OrdersController {
	@Autowired
	OrdersService ordersService;

	// 刷新前端页面订单展示
	@ResponseBody
	@RequestMapping("/ordersRefresh")
	public String ordersRefresh() {
		// 读取数据库中订单
		List<Orders> allorders = ordersService.findAllList();
		return JSON.toJSONString(allorders);
	}
	//更新订单（处理订单），取消处理订单
	@ResponseBody
	@RequestMapping("/ordersRight")
    public Object updateOrders(HttpServletRequest newOrders) {
		//获取传过来的数据 getParameter只能取出String的数据，所以前端不用转换数据类型
		String ordersid = newOrders.getParameter("ordersid");
		String orderstate = newOrders.getParameter("orderstate");
		Orders oldOrders = ordersService.findById(Integer.parseInt(ordersid));
		oldOrders.setOrderstate(orderstate);
		//更新
		ordersService.update(oldOrders);
		//更新成功后返回数据类型
		Map<String,Object> map = new HashMap<>();
		//查询数据库中对应id的state是修改成功
		Orders ordertest = ordersService.findById(Integer.parseInt(ordersid));
		if(ordertest.getOrderstate().equals(orderstate)) {
			map.put("rightCheck", true);
		}else {
			map.put("rightCheck", false);
		}
		return map;
	}
}
