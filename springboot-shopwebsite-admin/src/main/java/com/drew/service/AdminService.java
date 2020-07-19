package com.drew.service;

import java.util.List;
import java.util.Map;

import com.drew.model.Admin;

public interface AdminService {
	int add(Admin admin);

    int update(Admin admin);

    int delete(int id);

    Admin findById(int id);

    List<Admin> findAllList();
    
  //登录用
    Admin findByName(String name);
}
