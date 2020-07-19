package com.drew.dao.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drew.dao.OrdersDao;
import com.drew.model.Orders;

@Repository
public class OrdersDaoImpl implements OrdersDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Orders orders) {
        return jdbcTemplate.update("insert into orders  (ordersid,userid,goodsid,orderdate,orderstate ) values (?,?,?,?,? )",
        orders.getOrdersid(),orders.getUserid(),orders.getGoodsid(),orders.getOrderdate(),orders.getOrderstate());
    }

    @Override
    public int update(Orders orders) {
        return jdbcTemplate.update("UPDATE  orders  SET userid=?,goodsid=?,orderdate=?,orderstate=?"
        +" where ordersid=?",
            orders.getUserid(),orders.getGoodsid(),orders.getOrderdate(),orders.getOrderstate(),
            orders.getOrdersid());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from orders where ordersid=?",id);
    }

    @Override
    public Orders findById(int id) {
        List<Orders> list = jdbcTemplate.query("select * from orders where ordersid=?", new Object[]{id}, new BeanPropertyRowMapper<Orders>(Orders.class));
        if(list!=null && list.size()>0){
            Orders orders = list.get(0);
            return orders;
        }else{
             return null;
        }
    }

    @Override
    public List<Orders> findAllList() {
        List<Orders> list = jdbcTemplate.query("select * from orders", new Object[]{}, new BeanPropertyRowMapper<Orders>(Orders.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

}
