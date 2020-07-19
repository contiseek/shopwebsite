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
import com.drew.model.Address;
import com.drew.model.BuyShopping;
import com.drew.model.Goods;
import com.drew.model.User;
import com.drew.service.AddressService;
import com.drew.service.BuyShoppingService;
import com.drew.service.GoodsService;
import com.drew.service.UserService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;
	// 地址
		@ResponseBody
		@RequestMapping(value = "/useraddress") // , method = {RequestMethod.POST})
		public Object useraddress(HttpServletRequest userG) {
			// 获取传过来的数据
			String userId = userG.getParameter("userId");
			//Address address=addressService.findById(Integer.parseInt(userId));
			Map<String, Object> map = new HashMap<String, Object>();
			/*if(address!=null) {
				map.put("id", address.getAddressid());
				map.put("name", address.getGetname());
				map.put("number", address.getGetnum());
				map.put("address", address.getGetaddress());
			}else {*/
				map.put("id", 1);
				map.put("name", "李四");
				map.put("number", "123456789");
				map.put("address", "四川省成都市");
				//return JSON.toJSONString(allad);
			//}
			return map;

		}
}
