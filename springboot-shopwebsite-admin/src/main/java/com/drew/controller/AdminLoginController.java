package com.drew.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.drew.model.Admin;
import com.drew.service.AdminService;
@RestController
public class AdminLoginController {
	@Autowired
    AdminService adminService;
	@ResponseBody
	@RequestMapping(value = "/adminlogin")//, method = {RequestMethod.POST}) 
    public Object adminlogin(HttpServletRequest adminlogin) {
		//获取传过来的数据
		String username = adminlogin.getParameter("username");
		String password = adminlogin.getParameter("password");
		//数据库中取出数据
		Admin adminone = adminService.findByName(username);
		//定义传出数据类型为json
		Map<String,Object> map = new HashMap<>();
		//密码校验，此处注意传过来的是String类型，数据库中是int类型，username为数据库中的adminid
		if(String.valueOf(adminone.getAdminpassword()).equals(password)) {
			map.put("loginCheck",true);
		}else {
			map.put("loginCheck",false);
		}
		//System.out.println("getAdminName:"+adminone.getAdminid()+ "   password: "+adminone.getAdminpassword());
		//System.out.println("username:  " + username+"   password:"+password);
		//System.out.println("loginCheck :  " + JSON.toJSONString(map));
		return map;
	}
}
