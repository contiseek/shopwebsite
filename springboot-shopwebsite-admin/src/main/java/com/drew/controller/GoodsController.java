package com.drew.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.drew.model.Goods;
import com.drew.model.Orders;
import com.drew.service.GoodsService;
import com.drew.service.OrdersService;
import com.drew.service.Impl.GoodsServiceImpl;

@RestController

public class GoodsController {
	@Autowired
    GoodsService goodsService;
	//用来查询goods中是否有商品已经存在订单中
	OrdersService ordersService;
	@ResponseBody
	@RequestMapping("/goods")
    public String goods() {
		//读取数据库中所有商品
		List<Goods> allgoods = goodsService.findAllList();
		//Goods goods = goodsService.findById(2);
		//System.out.println("goods 1 "+goods.getGoodsname());
		//System.out.println("allGoods: "+allgoods);
		String jsonHead="{\r\n" + 
				"	\"code\": 0,\r\n" + 
				"	\"msg\": \"\",\r\n" + 
				"	\"count\": 1000,\r\n" + 
				"	\"data\": ";
		String returnStr=jsonHead+JSON.toJSONString(allgoods)+"}";
		return returnStr;
		//return "返回到页面成功";
    }
    //刷新前端页面商品展示
	@ResponseBody
	@RequestMapping("/goodsRefresh")
    public String goodsRefresh() {
		//读取数据库中所有商品
		List<Goods> allgoods = goodsService.findAllList();
		//Goods goods = goodsService.findById(2);
		//System.out.println("goods 1 "+goods.getGoodsname());
		//System.out.println("allGoods: "+allgoods);
		/*String jsonHead="{\r\n" + 
				"	\"code\": 0,\r\n" + 
				"	\"msg\": \"\",\r\n" + 
				"	\"count\": 1000,\r\n" + 
				"	\"data\": ";
		String returnStr=jsonHead+JSON.toJSONString(allgoods)+"}";
		*/
		return JSON.toJSONString(allgoods);
		//return "返回到页面成功";
    }
    //添加商品
	@ResponseBody
	@RequestMapping("/addGoods")
    public Object addGoods(HttpServletRequest newGoods) {
		//获取传过来的数据 getParameter只能取出String的数据，所以前端不用转换数据类型
		String goodsid = newGoods.getParameter("goodsid");
		String adminid = newGoods.getParameter("adminid");
		String goodsname = newGoods.getParameter("goodsname");
		String price = newGoods.getParameter("price");
		String picture = newGoods.getParameter("picture");
		String goodsnumber = newGoods.getParameter("goodsnumber");
		//存到Goods类的对象中
		Goods newgoods = new Goods();
		newgoods.setGoodsid(Integer.parseInt(goodsid));
		newgoods.setAdminid(Integer.parseInt(adminid));
		newgoods.setGoodsname(goodsname);
		newgoods.setPrice(price);
		newgoods.setPicture(picture);
		newgoods.setGoodsnumber(Integer.parseInt(goodsnumber));
		//System.out.println("String.valueOf(newgoods): "+String.valueOf(newgoods));
		
		//System.out.println("newgoods.getGoodsid()： "+newgoods.getGoodsid());
		//System.out.println("newgoods.getGoodsname()： "+newgoods.getGoodsname());
		//map用来返回前端结果
		Map<String,Object> map = new HashMap<>();
		//防止重复插入数据库，注意是查到为空表示可以插入数据
		if(goodsService.findById(newgoods.getGoodsid())==null) {
			//存入数据库
			goodsService.add(newgoods);
			//检查是否插入成功
			if(goodsService.findById(newgoods.getGoodsid())!=null) {
				map.put("addGoodsCheck",true);
			}else {
				map.put("addGoodsCheck",false);
			}
		}else {
			map.put("addGoodsCheck",false);
		}
		
		return map;
	}
	 //删除商品
		@ResponseBody
		@RequestMapping("/deleteGoods")
	    public Object deleteGoods(HttpServletRequest deleteGoods) {
			int id = Integer.parseInt(deleteGoods.getParameter("goodsid"));
			//读取数据库中所有商品
			//List<Goods> allgoods = goodsService.findAllList();
			//Goods goods = goodsService.findById(2);
			//System.out.println("goods 1 "+goods.getGoodsname());
			//System.out.println("allGoods: "+allgoods);
			//map用来返回前端
			Map<String,Object> map = new HashMap<>();
			//订单依赖不能删除
			//findById(id)不能用，是orderid
			/*List<Orders> allOrders = ordersService.findAllList();
			for(int i=0;i<allOrders.size();i++) {
				Orders orders = allOrders.get(i);
				if(orders.getGoodsid()==id) {
					map.put("addGoodsCheck",false);
					return map;
				}
			}*/
			//数据库中有才删除
			
			if(goodsService.findById(id)!=null) {
				goodsService.delete(id);
				//删了一遍没删掉
				if(goodsService.findById(id)!=null) {
					map.put("deleteCheck",false);
					return map;
				}else {
					map.put("deleteCheck",true);
					return map;
				}
			}else {//数据库中没有
				map.put("deleteCheck",false);
			}
			map.put("deleteCheck",false);
				return map;
			//return "返回到页面成功";
	    }
		
		////修改商品
		@ResponseBody
		@RequestMapping("/updateGoods")
	    public Object updateGoods(HttpServletRequest newGoods) {
			//获取传过来的数据 getParameter只能取出String的数据，所以前端不用转换数据类型
			String goodsid = newGoods.getParameter("goodsid");
			String adminid = newGoods.getParameter("adminid");
			String goodsname = newGoods.getParameter("goodsname");
			String price = newGoods.getParameter("price");
			String picture = newGoods.getParameter("picture");
			String goodsnumber = newGoods.getParameter("goodsnumber");
			//存到Goods类的对象中
			Goods newgoods = new Goods();
			newgoods.setGoodsid(Integer.parseInt(goodsid));
			newgoods.setAdminid(Integer.parseInt(adminid));
			newgoods.setGoodsname(goodsname);
			newgoods.setPrice(price);
			newgoods.setPicture(picture);
			newgoods.setGoodsnumber(Integer.parseInt(goodsnumber));
			//System.out.println("String.valueOf(newgoods): "+String.valueOf(newgoods));
			
			//System.out.println("newgoods.getGoodsid()： "+newgoods.getGoodsid());
			//System.out.println("newgoods.getGoodsname()： "+newgoods.getGoodsname());
			//map用来返回前端结果
			Map<String,Object> map = new HashMap<>();
			//注意是查到“不为空”表示可以更新数据
			if(goodsService.findById(newgoods.getGoodsid())!=null) {
				//更新数据库
				goodsService.update(newgoods);
				//检查是否更新成功
				if(goodsService.findById(newgoods.getGoodsid())!=null) {
					map.put("updateGoodsCheck",true);
				}else {
					map.put("updateGoodsCheck",false);
				}
			}else {
				map.put("updateGoodsCheck",false);
			}
			return map;
		}
		
	////修改商品库存
		@ResponseBody
		@RequestMapping("/roomUpdate")
	    public Object roomUpdate(HttpServletRequest newGoods) {
			//获取传过来的数据 getParameter只能取出String的数据，所以前端不用转换数据类型
			String goodsid = newGoods.getParameter("goodsid");
			String goodsnumber = newGoods.getParameter("goodsnumber");
			//System.out.println("goodsid "+goodsid+" goodsnumber"+goodsnumber);
			Map<String,Object> map = new HashMap<>();
			//注意是查到“不为空”表示可以更新数据
			if(goodsService.findById(Integer.parseInt(goodsid))!=null) {
				//读取数据库
				Goods oldgoods = goodsService.findById(Integer.parseInt(goodsid));
				//修改内容
				//oldgoods.setGoodsid(Integer.parseInt(goodsid));
				oldgoods.setGoodsnumber(Integer.parseInt(goodsnumber));
				//System.out.println("oldgoods.setGoodsid "+oldgoods.getGoodsid()+" oldgoods.getGoodsnumber("+oldgoods.getGoodsnumber());
				//更新数据库
				goodsService.update(oldgoods);
				//检查是否更新成功
				if(goodsService.findById(oldgoods.getGoodsid())!=null) {
					//Goods oldgoods2 = goodsService.findById(oldgoods.getGoodsid());
					//System.out.println("goodsid "+oldgoods2.getGoodsid()+" goodsnumber"+oldgoods2.getGoodsnumber());
					map.put("roomUpdateCheck",true);
				}else {
					map.put("roomUpdateCheck",false);
				}
			}else {
				map.put("roomUpdateCheck",false);
			}
			return map;
		}
}
