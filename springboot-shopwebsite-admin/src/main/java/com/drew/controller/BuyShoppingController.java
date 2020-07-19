package com.drew.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.drew.dao.UserDao;
import com.drew.model.BuyShopping;
import com.drew.model.Goods;
import com.drew.model.User;
import com.drew.service.BuyShoppingService;
import com.drew.service.GoodsService;
import com.drew.service.UserService;

@RestController
public class BuyShoppingController {
	//***********************使用多个Service会出现500错误，无法访问数据库
	@Autowired
	BuyShoppingService buyShoppingService;

	// 显示购物车
	@ResponseBody
	@RequestMapping(value = "/buycar") // , method = {RequestMethod.POST})
	public Object buycar(HttpServletRequest httpServletRequest) {
		// 获取传过来的用户名
		String id=httpServletRequest.getParameter("userid");
		
		List<BuyShopping> allList = buyShoppingService.findAllListById(Integer.parseInt(id));
		if(allList!=null) {
			return JSON.toJSONString(allList);
		}else {
			return 0;
		}
	}
}
