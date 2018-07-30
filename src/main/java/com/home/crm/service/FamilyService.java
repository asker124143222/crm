package com.home.crm.service;

import com.home.crm.entity.Family;

/**
 * @Author: xu.dm
 * @Date: 2018/7/19 22:05
 * @Description:
 */
public interface FamilyService {
    Family findById(Long id);

    Family findByCustomerId(Long customerId);

    Family save(Family family);

    boolean delete(Long id);

}
