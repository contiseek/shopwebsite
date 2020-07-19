package com.drew.service;


import java.util.List;

import com.drew.model.Goods;

public interface GoodsService {
	int add(Goods goods);

	int update(Goods goods);

	int delete(int id);

	Goods findById(int id);

	List<Goods> findAllList();

	
}
