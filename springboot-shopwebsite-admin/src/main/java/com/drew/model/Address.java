package com.drew.model;


public class Address {

   private int addressid;
   private int userid;
   private String getname;
   private String getnum;
   private String getaddress;
   public void setAddressid(int addressid) {
        this.addressid = addressid;
    }
    public int getAddressid() {
        return addressid;
    }

   public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getUserid() {
        return userid;
    }

   public void setGetname(String getname) {
        this.getname = getname;
    }
    public String getGetname() {
        return getname;
    }

   public void setGetNum(String getnum) {
        this.getnum = getnum;
    }
    public String getGetnum() {
        return getnum;
    }

   public void setGetaddress(String getaddress) {
        this.getaddress = getaddress;
    }
    public String getGetaddress() {
        return getaddress;
    }

}