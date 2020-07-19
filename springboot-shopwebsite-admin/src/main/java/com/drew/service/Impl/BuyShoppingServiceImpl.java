package com.drew.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.dao.BuyShoppingDao;
import com.drew.model.BuyShopping;
import com.drew.service.BuyShoppingService;

@Service
public class BuyShoppingServiceImpl implements BuyShoppingService{
	@Autowired
	BuyShoppingDao buyShoppingDao;
	
	@Override
	public int add(BuyShopping buyShopping) {
		return buyShoppingDao.add(buyShopping);
	}
	
	

	@Override
	public int update(BuyShopping buyShopping) {
		return buyShoppingDao.update(buyShopping);
	}

	@Override
	public int delete(int id) {
		return buyShoppingDao.delete(id);
	}

	@Override
	public BuyShopping findById(int id) {
		return buyShoppingDao.findById(id);
	}

    @Override
	public List<BuyShopping> findAllList(){
    	return buyShoppingDao.findAllList();
    }
    //List<BuyShopping> findAllListById(int id);
    @Override
	public List<BuyShopping> findAllListById(int id){
    	return buyShoppingDao.findAllListById(id);
    }
}
