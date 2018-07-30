package com.home.crm.service;

import com.home.crm.entity.Customer;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/6/10 20:10
 * @Description:
 */

public interface CustomerService {
    List<Customer> findAll();

    List<Customer> findAll(Sort sort);

    Optional<Customer> findById(Long id);

    List<Customer> findByCustomerNameContains(String customerName);

    List<Customer> findByIdCard(String idCard);

    Customer findByAgreementNum(String agreementNum);

    Customer save(Customer customer);

    Customer saveAndFlush(Customer customer);

    boolean delete(Long id);
}
