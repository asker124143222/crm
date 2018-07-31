package com.home.crm.service.service.impl;

import com.home.crm.entity.Family;
import com.home.crm.repository.FamilyRepository;
import com.home.crm.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xu.dm
 * @Date: 2018/7/19 22:06
 * @Description:
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Family findById(Long id) {
        return familyRepository.findById(id).orElse(new Family());
    }

    @Override
    public Family findByCustomerId(Long customerId) {
        return familyRepository.findByCustomerId(customerId).get(0);
    }

    @Override
    public Family save(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public boolean delete(Long id) {
        try {
            familyRepository.deleteById(id);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int deleteByCustomerId(Long id) {
        return familyRepository.deleteFamiliesByCustomerId(id);
    }
}
