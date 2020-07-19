package com.drew.controller;

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
import com.drew.model.Admin;
import com.drew.model.User;
import com.drew.service.AddressService;
import com.drew.service.AdminService;
import com.drew.service.UserService;

@RestController
public class UserLoginController {
	@Autowired
	UserService userService;
	
	// 定义传出数据类型为json
	Map<String, Object> sessionmap = new HashMap<>();

	
	//getId
	@ResponseBody
	@RequestMapping(value = "/getUserId") // , method = {RequestMethod.POST})
	public Object getUserId(HttpServletRequest userG) {
		// 获取传过来的数据
		String username=userG.getParameter("username");
		User user = userService.findByName(username);
		int userId = user.getUserid();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId",userId);
		return map;
	}
	

	// 个人信息
	@ResponseBody
	@RequestMapping(value = "/userinfo") // , method = {RequestMethod.POST})
	public Object userinfo(HttpServletRequest userinfo) {
		// 获取传过来的数据
		String username = userinfo.getParameter("username");
		int userid = userService.findByName(username).getUserid();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userid", userid);
		map.put("username", userService.findByName(username).getUsername());
		map.put("password", userService.findByName(username).getUserpassword());
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/userlogin") // , method = {RequestMethod.POST})
	public Object userlogin(HttpServletRequest userlogin) {
		// 获取传过来的数据
		String username = userlogin.getParameter("username");
		String password = userlogin.getParameter("password");
		// 数据库中取出数据
		User userone = userService.findByName(username);
		// 定义传出数据类型为json
		// Map<String, Object> map = new HashMap<>();
		// 密码校验，此处注意传过来的是String类型，数据库中是int类型，username为数据库中的adminid
		if (String.valueOf(userone.getUserpassword()).equals(password)) {
			sessionmap.put("loginCheck", true);
			sessionmap.put("username", username);
		} else {
			sessionmap.put("loginCheck", false);
		}
		// System.out.println("getAdminName:"+adminone.getAdminid()+ " password:
		// "+adminone.getAdminpassword());
		// System.out.println("username: " + username+" password:"+password);
		// System.out.println("loginCheck : " + JSON.toJSONString(map));
		return sessionmap;
	}

	@ResponseBody
	@RequestMapping(value = "/userSession") // , method = {RequestMethod.POST})
	public Object userSession() {
		// 数据库中取出数据
		// 密码校验，此处注意传过来的是String类型，数据库中是int类型，username为数据库中的adminid
		return sessionmap;
	}

//用户注册
	@ResponseBody
	@RequestMapping(value = "/userregist") // , method = {RequestMethod.POST})
	public Object userregist(HttpServletRequest userregist) {
		// 获取传过来的数据
		String username = userregist.getParameter("username");
		String password = userregist.getParameter("password");
		// 获取一个新的id不重复，不能插入重复id
		int newId = 0;
		for (int i = 0; i < 99; i++) {
			i++;
			if (userService.findById(i) == null) {
				newId = i;
				break;
			}
		}
		User usernew = new User();
		usernew.setAdminid(1);
		usernew.setUserid(newId);
		usernew.setUsername(username);
		usernew.setUserpassword(password);

		userService.add(usernew);
		// 数据库有就返回true
		if (userService.findByName(username) != null) {
			sessionmap.put("registCheck", true);
			sessionmap.put("username", username);
		} else {
			sessionmap.put("registCheck", false);
		}
		return sessionmap;
	}
}
