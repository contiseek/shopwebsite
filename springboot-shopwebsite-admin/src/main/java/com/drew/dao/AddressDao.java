package com.drew.dao;

import java.util.List;
import java.util.Map;

import com.drew.model.Address;

public interface AddressDao {
	int add(Address address);

    int update(Address address);

    int delete(int id);

    Address findById(int id);

    List<Address> findAllList();
    //findByUserId()
    Address findByUserId(int id);
}
