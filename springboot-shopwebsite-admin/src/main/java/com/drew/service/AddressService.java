package com.drew.service;

import java.util.List;
import java.util.Map;

import com.drew.model.Address;

public interface AddressService {
	int add(Address address);

    int update(Address address);

    int delete(int id);

    Address findById(int id);

    List<Address> findAllList();

    Address findByUserId(int id);
}