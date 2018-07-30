package com.home.crm.repository;

import com.home.crm.entity.Baby;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/7/17 22:48
 * @Description:
 */

public interface BabyRepository extends JpaRepository<Baby,Long> {

     List<Baby> findByCustomerId(Long customerId);

     @Modifying
     @Query(value = "delete from baby where customerId=?1",nativeQuery = true)
     int deleteByCustomerId(Long customerId);

     int deleteBabiesByCustomerId(Long customerId);
}
