package com.drew.model;

public class Admin {

    private int adminid;
    private String adminname;
    private String adminpassword;
    public void setAdminid(int adminid) {
         this.adminid = adminid;
     }
     public int getAdminid() {
         return adminid;
     }

    public void setAdminname(String adminname) {
         this.adminname = adminname;
     }
     public String getAdminname() {
         return adminname;
     }

    public void setAdminpassword(String adminpassword) {
         this.adminpassword = adminpassword;
     }
     public String getAdminpassword() {
         return adminpassword;
     }

}