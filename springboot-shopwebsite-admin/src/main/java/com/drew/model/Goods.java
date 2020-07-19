package com.drew.model;

public class Goods {

    private int goodsid;
    private int adminid;
    private String goodsname;
    private String price;
    private String picture;
    private int goodsnumber;
    public void setGoodsid(int goodsid) {
         this.goodsid = goodsid;
     }
     public int getGoodsid() {
         return goodsid;
     }

    public void setAdminid(int adminid) {
         this.adminid = adminid;
     }
     public int getAdminid() {
         return adminid;
     }

    public void setGoodsname(String goodsname) {
         this.goodsname = goodsname;
     }
     public String getGoodsname() {
         return goodsname;
     }

    public void setPrice(String price) {
         this.price = price;
     }
     public String getPrice() {
         return price;
     }

    public void setPicture(String picture) {
         this.picture = picture;
     }
     public String getPicture() {
         return picture;
     }
     public void setGoodsnumber(int goodsnumber) {
         this.goodsnumber = goodsnumber;
     }
     public int getGoodsnumber() {
         return goodsnumber;
     }

}