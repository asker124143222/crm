package com.home.crm.service;

import com.home.crm.entity.Baby;

import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2018/7/19 21:52
 * @Description:
 */
public interface BabyService {
    Baby findById(Long id);

    List<Baby> findAllByCustomerId(Long customerId);



    Baby save(Baby baby);

    boolean delete(Long id);
}
