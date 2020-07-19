package com.drew.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drew.dao.BuyShoppingDao;
import com.drew.model.BuyShopping;

import java.util.List;
import java.util.Map;

/**
* buyshopping
* @author 大狼狗 2020-07-06
*/
@Repository
public class BuyShoppingDaoImpl implements BuyShoppingDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(BuyShopping buyshopping) {
        return jdbcTemplate.update("insert into buyshopping  (shoppinggoodsid,userid,goodsid ) values (?,?,? )",
        buyshopping.getShoppinggoodsid(),buyshopping.getUserid(),buyshopping.getGoodsid());
    }

    @Override
    public int update(BuyShopping buyshopping) {
        return jdbcTemplate.update("UPDATE  buyshopping  SET userid=?,goodsid=?"
        +" where shoppinggoodsid=?",
            buyshopping.getUserid(),buyshopping.getGoodsid(),
            buyshopping.getShoppinggoodsid());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from buyshopping where shoppinggoodsid=?",id);
    }

    @Override
    public BuyShopping findById(int id) {
        List<BuyShopping> list = jdbcTemplate.query("select * from buyshopping where shoppinggoodsid=?", 
        		new Object[]{id}, new BeanPropertyRowMapper<BuyShopping>(BuyShopping.class));
        if(list!=null && list.size()>0){
            BuyShopping buyshopping = list.get(0);
            return buyshopping;
        }else{
             return null;
        }
    }

    @Override
    public List<BuyShopping> findAllList() {
        List<BuyShopping> list = jdbcTemplate.query("select * from buyshopping", new Object[]{},
        		new BeanPropertyRowMapper<BuyShopping>(BuyShopping.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

}
