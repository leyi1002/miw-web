package com.miw.bean;


import java.io.Serializable;

/**
 * Created by ZhangJian on 2015-7-16.
 */
public class UserPrimary implements Serializable {

    private Integer id;

    private String username;

    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

     @Override
     public String toString() {
         return "UserPrimary{" +
                 "id=" + id +
                 ", username='" + username + '\'' +
                 ", password='" + password + '\'' +
                 '}';
     }
 }