package com.miw.bean;


import java.io.Serializable;

/**
 * Created by ZhangJian on 2015-7-16.
 */
public class UserPrimary implements Serializable {

    private static final String serialVersionUID ="-5809782578272943999L";

    private String id;

    private String username;

    private String password;

    private UserInfo userInfo;

    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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