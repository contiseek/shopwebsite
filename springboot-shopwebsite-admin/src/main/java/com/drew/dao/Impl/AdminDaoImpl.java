package com.drew.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drew.dao.AdminDao;
import com.drew.model.Admin;

import java.util.List;
import java.util.Map;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Admin admin) {
		return jdbcTemplate.update("insert into admin  (adminid,adminname,adminpassword ) values (?,?,? )",
				admin.getAdminid(), admin.getAdminname(), admin.getAdminpassword());
	}

	@Override
	public int update(Admin admin) {
		return jdbcTemplate.update("UPDATE  admin  SET adminname=?,adminpassword=?" + " where adminid=?",
				admin.getAdminname(), admin.getAdminpassword(), admin.getAdminid());
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update("DELETE from admin where adminid=?", id);
	}

	@Override
	public Admin findById(int id) {
		List<Admin> list = jdbcTemplate.query("select * from admin where adminid=?", new Object[] { id },
				new BeanPropertyRowMapper<Admin>(Admin.class));
		if (list != null && list.size() > 0) {
			Admin admin = list.get(0);
			return admin;
		} else {
			return null;
		}
	}
	
	//登录用
	@Override
	public Admin findByName(String name) {
		List<Admin> list = jdbcTemplate.query("select * from admin where adminname=?", new Object[] { name },
				new BeanPropertyRowMapper<Admin>(Admin.class));
		if (list != null && list.size() > 0) {
			Admin admin = list.get(0);
			return admin;
		} else {
			return null;
		}
	}
	@Override
	public List<Admin> findAllList() {
		List<Admin> list = jdbcTemplate.query("select * from admin", new Object[] {},
				new BeanPropertyRowMapper<Admin>(Admin.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

}
