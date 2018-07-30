package com.home.crm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author: xu.dm
 * @Date: 2018/6/10 17:14
 * @Description:
 */

@Entity
public class User {
    @Id
    @GenericGenerator(name="generator",strategy = "native")
    @GeneratedValue(generator = "generator")
    private Long userId;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
