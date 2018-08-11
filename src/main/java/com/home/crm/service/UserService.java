package com.home.crm.service;

import com.home.crm.entity.User;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 16:45
 * @Description:
 */

public interface UserService {
    User findByUserName(String userName);
}
