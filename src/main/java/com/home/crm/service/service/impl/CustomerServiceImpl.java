package com.home.crm.service.service.impl;

import com.home.crm.entity.Customer;
import com.home.crm.repository.CustomerRepository;
import com.home.crm.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/6/10 20:18
 * @Description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAllByPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return customerRepository.findAll(sort);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findByCustomerNameContains(String customerName) {
        return customerRepository.findByCustomerNameContains(customerName);
    }

    @Override
    public Page<Customer> findByCustomerNameContains(String customerName, Pageable pageable) {
        return customerRepository.findCustomersByCustomerNameContains(customerName,pageable);
    }

    @Override
    public List<Customer> findByIdCard(String idCard) {
        return customerRepository.findByIdCard(idCard);
    }

    @Override
    public Customer findByAgreementNum(String agreementNum) {
        return customerRepository.findByAgreementNum(agreementNum);
    }

    @Override
    public Customer save(Customer customer) {
       return customerRepository.save(customer);
    }

    @Override
    public Customer saveAndFlush(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public boolean delete(Long id) {
        try {
            customerRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
