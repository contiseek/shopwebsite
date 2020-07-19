package com.drew.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.dao.OrdersDao;
import com.drew.model.Orders;
import com.drew.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	OrdersDao ordersDao;
	@Override
	public int add(Orders orders) {
		return ordersDao.add(orders);
	}

    @Override
	public int update(Orders orders){
		return ordersDao.update(orders);
    }

    @Override
	public int delete(int id) {
    	return ordersDao.delete(id);
    }

    @Override
	public Orders findById(int id) {
    	return ordersDao.findById(id);
    }

    @Override
	public List<Orders> findAllList(){
    	return ordersDao.findAllList();
    }
}
