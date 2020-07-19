package com.drew.dao;

import java.util.List;
import java.util.Map;

import com.drew.model.Orders;

public interface OrdersDao {

	    int add(Orders orders);

	    int update(Orders orders);

	    int delete(int id);

	    Orders findById(int id);

	    List<Orders> findAllList();
}
