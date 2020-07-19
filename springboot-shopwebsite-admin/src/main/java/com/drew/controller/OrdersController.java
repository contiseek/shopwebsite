package com.drew.controller;

import java.util.ArrayList;
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
import com.drew.model.Address;
import com.drew.model.BuyShopping;
import com.drew.model.Goods;
import com.drew.model.Orders;
import com.drew.model.User;
import com.drew.service.AddressService;
import com.drew.service.BuyShoppingService;
import com.drew.service.GoodsService;
import com.drew.service.OrdersService;
import com.drew.service.UserService;

@RestController
public class OrdersController {
	@Autowired
	OrdersService ordersservice;
	// 用户
	UserService userservice;
	GoodsService goodsservice;
	AddressService addressservice;

	// 显示购物车
	@ResponseBody
	@RequestMapping(value = "/myorders") // , method = {RequestMethod.POST})
	public Object myorders(HttpServletRequest httpServletRequest) {
		// 获取传过来的用户名
		String username = httpServletRequest.getParameter("username");
		User user = userservice.findByName(username);
		int userid = user.getUserid();
		/* 读取数据到list */
		List<Map<String, Object>> myorderslist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Orders> allorders = ordersservice.findAllListById(userid);
		for (int i = 0; i < allorders.size(); i++) {
			// 从购物车表和商品表获取数据
			map.put("id", allorders.get(i).getOrdersid());
			//map.put("goodsid", allorders.get(i).getGoodsid());
			int goodsid = allorders.get(i).getGoodsid();
			int userId = allorders.get(i).getUserid();
			Goods goods = goodsservice.findById(goodsid);
			map.put("goodsname", goods.getGoodsname());
			map.put("price", goods.getPrice());
			map.put("date", allorders.get(i).getOrderdate());
			Address address = addressservice.findByUserId(userId);
			map.put("address",address.getGetaddress());
			map.put("state",allorders.get(i).getOrderstate());
			myorderslist.add(map);
		}
		return JSON.toJSONString(myorderslist);
	}

	// 管理
	// 刷新前端页面订单展示
	@ResponseBody
	@RequestMapping("/ordersRefresh")
	public String ordersRefresh() {
		// 读取数据库中订单
		List<Orders> allorders = ordersservice.findAllList();
		return JSON.toJSONString(allorders);
	}

	// 更新订单（处理订单），取消处理订单
	@ResponseBody
	@RequestMapping("/ordersRight")
	public Object updateOrders(HttpServletRequest newOrders) {
		// 获取传过来的数据 getParameter只能取出String的数据，所以前端不用转换数据类型
		String ordersid = newOrders.getParameter("ordersid");
		String orderstate = newOrders.getParameter("orderstate");
		Orders oldOrders = ordersservice.findById(Integer.parseInt(ordersid));
		oldOrders.setOrderstate(orderstate);
		// 更新
		ordersservice.update(oldOrders);
		// 更新成功后返回数据类型
		Map<String, Object> map = new HashMap<>();
		// 查询数据库中对应id的state是修改成功
		Orders ordertest = ordersservice.findById(Integer.parseInt(ordersid));
		if (ordertest.getOrderstate().equals(orderstate)) {
			map.put("rightCheck", true);
		} else {
			map.put("rightCheck", false);
		}
		return map;
	}
}
