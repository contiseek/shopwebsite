package com.drew.dao;

import java.util.List;
import java.util.Map;

import com.drew.model.User;

public interface UserDao {
	int add(User user);

    int update(User user);

    int delete(int id);

    User findById(int id);

    List<User> findAllList();

}