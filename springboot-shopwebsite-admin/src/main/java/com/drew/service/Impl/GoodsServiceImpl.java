package com.drew.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.dao.GoodsDao;
import com.drew.model.Goods;
import com.drew.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	GoodsDao goodsDao;

	@Override
	public int add(Goods goods) {
		return goodsDao.add(goods);
	}

	@Override
	public int delete(int id) {
		return goodsDao.delete(id);
	}

	@Override
	public int update(Goods goods) {
		return goodsDao.update(goods);
	}

	@Override
	public Goods findById(int id) {
		return goodsDao.findById(id);
	}

	@Override
	public List<Goods> findAllList() {
		return goodsDao.findAllList();
	}
}