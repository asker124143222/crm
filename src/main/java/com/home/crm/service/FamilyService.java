package com.home.crm.service;

import com.home.crm.entity.Family;

import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2018/7/19 22:05
 * @Description:
 */
public interface FamilyService {
    Family findById(Long id);

    List<Family> findByCustomerId(Long customerId);

    Family save(Family family);

    boolean delete(Long id);

    int deleteByCustomerId(Long id);

}
