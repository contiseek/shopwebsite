package com.drew.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.drew.dao.GoodsDao;
import com.drew.model.Goods;

import java.util.List;


@Repository
public class GoodsDaoImpl implements GoodsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Goods goods) {
        return jdbcTemplate.update("insert into goods  (goodsid,adminid,goodsname,price,picture,goodsnumber ) values (?,?,?,?,?,? )",
        goods.getGoodsid(),goods.getAdminid(),goods.getGoodsname(),goods.getPrice(),goods.getPicture(),goods.getGoodsnumber());
    }

    @Override
    public int update(Goods goods) {
        return jdbcTemplate.update("UPDATE  goods  SET adminid=?,goodsname=?,price=?,picture=?,goodsnumber=?"
        +" where goodsid=?",
            goods.getAdminid(),goods.getGoodsname(),goods.getPrice(),goods.getPicture(),goods.getGoodsnumber(),
            goods.getGoodsid());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from goods where goodsid=?",id);
    }

    @Override
    public Goods findById(int id) {
        List<Goods> list = jdbcTemplate.query("select * from goods where goodsid=?", new Object[]{id}, new BeanPropertyRowMapper<Goods>(Goods.class));
        if(list!=null && list.size()>0){
            Goods goods = list.get(0);
            return goods;
        }else{
             return null;
        }
    }

    @Override
    public List<Goods> findAllList() {
        List<Goods> list = jdbcTemplate.query("select *from goods", new Object[]{}, new BeanPropertyRowMapper<Goods>(Goods.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }

}
