package com.drew.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drew.dao.UserDao;
import com.drew.model.User;
import com.drew.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;
	
	@Override
	public int add(User user) {
		return userDao.add(user);
	}

    @Override
	public int update(User user) {
    	return userDao.update(user);
    }

    @Override
	public int delete(int id) {
    	return userDao.delete(id);
    }

    @Override
	public User findById(int id) {
    	return userDao.findById(id);
    }

     @Override
	public List<User> findAllList(){
    	 return userDao.findAllList();
     }
}
