package com.drew.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.dao.AdminDao;
import com.drew.model.Admin;
import com.drew.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDao adminDao;
	
	@Override
	public int add(Admin admin) {
		return adminDao.add(admin);
	}

	@Override
	public int update(Admin admin) {
		return adminDao.update(admin);
	}
	
	@Override
	public int delete(int id) {
		return adminDao.delete(id);
	}
 
	@Override
	public Admin findById(int id) {
		return adminDao.findById(id);
	}

	@Override
	public List<Admin> findAllList(){
		return adminDao.findAllList();
	}
	@Override
	public Admin findByName(String name){
		return adminDao.findByName(name);
	}
}
