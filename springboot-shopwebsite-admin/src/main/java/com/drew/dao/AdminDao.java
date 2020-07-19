package com.drew.dao;

import java.util.List;
import java.util.Map;

import com.drew.model.Admin;

public interface AdminDao {

    int add(Admin admin);

    int update(Admin admin);

    int delete(int id);

    Admin findById(int id);
    
    //登录用
    Admin findByName(String name);

    List<Admin> findAllList();

}
