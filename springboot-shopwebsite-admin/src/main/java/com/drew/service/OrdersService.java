package com.drew.service;

import java.util.List;
import java.util.Map;

import com.drew.model.Orders;

public interface OrdersService {
	int add(Orders orders);

    int update(Orders orders);

    int delete(int id);

    Orders findById(int id);

    List<Orders> findAllList();
    
    List<Orders> findAllListById(int id);
}
