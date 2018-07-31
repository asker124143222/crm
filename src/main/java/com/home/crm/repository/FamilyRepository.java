package com.home.crm.repository;

import com.home.crm.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/7/18 22:27
 * @Description:
 */
public interface FamilyRepository extends JpaRepository<Family,Long> {

    List<Family> findByCustomerId(Long customerId);

    int deleteFamiliesByCustomerId(Long customerId);
}
