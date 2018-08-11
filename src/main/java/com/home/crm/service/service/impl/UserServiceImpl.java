package com.home.crm.service.service.impl;

import com.home.crm.entity.User;
import com.home.crm.repository.UserRepository;
import com.home.crm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 16:47
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
