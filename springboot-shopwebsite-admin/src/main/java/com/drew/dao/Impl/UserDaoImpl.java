package com.drew.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drew.dao.UserDao;
import com.drew.model.User;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(User user) {
        return jdbcTemplate.update("insert into user  (userid,adminid,username,userpassword ) values (?,?,?,? )",
        user.getUserid(),user.getAdminid(),user.getUsername(),user.getUserpassword());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE  user  SET adminid=?,username=?,userpassword=?"
        +" where userid=?",
            user.getAdminid(),user.getUsername(),user.getUserpassword(),
            user.getUserid());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from user where userid=?",id);
    }
    
    
    @Override
    public User findById(int id) {
        List<User> list = jdbcTemplate.query("select * from user where userid=?", new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
        if(list!=null && list.size()>0){
            User user = list.get(0);
            return user;
        }else{
             return null;
        }
    }
    
    @Override
    public User findByName(String name) {
        List<User> list = jdbcTemplate.query("select * from user where username=?", new Object[]{name}, new BeanPropertyRowMapper<User>(User.class));
        if(list!=null && list.size()>0){
            User user = list.get(0);
            return user;
        }else{
             return null;
        }
    }
    
    @Override
    public List<User> findAllList() {
        List<User> list = jdbcTemplate.query("select * from user", new Object[]{}, new BeanPropertyRowMapper<User>(User.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

}

