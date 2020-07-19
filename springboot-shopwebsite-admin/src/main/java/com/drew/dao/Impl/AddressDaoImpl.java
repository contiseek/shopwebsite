package com.drew.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drew.dao.AddressDao;
import com.drew.model.Address;

import java.util.List;
import java.util.Map;


@Repository
public class AddressDaoImpl implements AddressDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Address address) {
        return jdbcTemplate.update("insert into address  (addressid,userid,getname,getnum,getaddress ) values (?,?,?,?,? )",
        address.getAddressid(),address.getUserid(),address.getGetname(),address.getGetnum(),address.getGetaddress());
    }

    @Override
    public int update(Address address) {
        return jdbcTemplate.update("UPDATE  address  SET userid=?,getname=?,getnum=?,getaddress=?"
        +" where addressid=?",
            address.getUserid(),address.getGetname(),address.getGetnum(),address.getGetaddress(),
            address.getAddressid());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from address where addressid=?",id);
    }

    @Override
    public Address findById(int id) {
        List<Address> list = jdbcTemplate.query("select * from address where addressid=?", new Object[]{id}, new BeanPropertyRowMapper<Address>(Address.class));
        if(list!=null && list.size()>0){
            Address address = list.get(0);
            return address;
        }else{
             return null;
        }
    }

    @Override
    public List<Address> findAllList() {
        List<Address> list = jdbcTemplate.query("select * from address", new Object[]{}, new BeanPropertyRowMapper<Address>(Address.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }


    @Override
    public Address findByUserId(int id) {
        List<Address> list = jdbcTemplate.query("select * from address where userid=?", new Object[]{id}, new BeanPropertyRowMapper<Address>(Address.class));
        if(list!=null && list.size()>0){
            Address address = list.get(0);
            return address;
        }else{
             return null;
        }
    }

}