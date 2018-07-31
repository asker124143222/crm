package com.home.crm.repository;

import com.home.crm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/6/10 17:22
 * @Description:
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Page<Customer> findCustomersByCustomerNameContains(String customerName, Pageable pageable);

    List<Customer> findByCustomerNameContains(String customerName);

    List<Customer> findByIdCard(String idCard);

    Customer findByAgreementNum(String agreementNum);

}
