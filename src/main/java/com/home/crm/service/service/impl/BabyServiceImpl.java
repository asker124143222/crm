package com.home.crm.service.service.impl;

import com.home.crm.entity.Baby;
import com.home.crm.repository.BabyRepository;
import com.home.crm.service.BabyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2018/7/19 21:57
 * @Description:
 */
@Service
public class BabyServiceImpl implements BabyService {

    @Resource
    private BabyRepository babyRepository;

    @Override
    public Baby findById(Long id) {
        return babyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Baby> findAllByCustomerId(Long customerId) {
        return babyRepository.findByCustomerId(customerId);
    }

    @Override
    public Baby save(Baby baby) {
        return babyRepository.save(baby);
    }

    @Override
    public boolean delete(Long id) {
        try {
            babyRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int deleteByCustomerId(Long customerId) {
        return babyRepository.deleteByCustomerId(customerId);
    }

    @Override
    public int deleteBabiesByCustomerId(Long customerId) {
        return babyRepository.deleteBabiesByCustomerId((customerId));
    }
}
