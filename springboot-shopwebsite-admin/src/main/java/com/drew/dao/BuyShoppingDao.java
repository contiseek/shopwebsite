package com.drew.dao;

import java.util.List;
import java.util.Map;

import com.drew.model.BuyShopping;


public interface BuyShoppingDao {

    int add(BuyShopping buyShopping);

    int update(BuyShopping buyShopping);

    int delete(int id);

    BuyShopping findById(int id);

    List<BuyShopping> findAllList();
    
    List<BuyShopping> findAllListById(int id);

}