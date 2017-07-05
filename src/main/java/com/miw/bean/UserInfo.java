package com.miw.bean;

import java.io.Serializable;

/**
 * Created by J lai on 2017/6/27 0027.
 */
public class UserInfo implements Serializable{

    private static final String serialVersionUID ="-5809782578272943000L";

    private String email;
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
