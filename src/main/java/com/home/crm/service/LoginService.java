package com.home.crm.service;

import com.home.crm.model.LoginResult;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 21:34
 * @Description:
 */
public interface LoginService {
    LoginResult login(String userName,String password);
    void logout();
}
