package com.drew.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.dao.AddressDao;
import com.drew.model.Address;
import com.drew.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	AddressDao addressDao;
	
	@Override
	public int add(Address address) {
		return addressDao.add(address);
	}
	
	@Override
	public int update(Address address) {
		return addressDao.update(address);
	}
	
	@Override
	public int delete(int id) {
		return addressDao.delete(id);
	}
    
	@Override
	public Address findById(int id) {
		return addressDao.findById(id);
	}
	
	@Override
	public List<Address> findAllList(){
		return addressDao.findAllList();
	}


    ;
}
