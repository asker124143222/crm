package com.home.crm.service.service.impl;

import com.home.crm.entity.SysRole;
import com.home.crm.model.ISysRolePermission;
import com.home.crm.repository.RoleRepository;
import com.home.crm.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/9/5 22:16
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleRepository roleRepository;

    @Override
    public Page<SysRole> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Optional<SysRole> findById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public Page<SysRole> findAllByRoleContains(String role, Pageable pageable) {
        return roleRepository.findAllByRoleContains(role,pageable);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return roleRepository.save(sysRole);
    }

    @Override
    public boolean checkRoleExists(String role) {
        SysRole sysRole = roleRepository.findSysRoleByRole(role);
        if(sysRole!=null)
            return true;
        else
            return false;
    }

    @Override
    public boolean checkRoleExists(String oldRole, String newRole) {
        SysRole sysRole = roleRepository.findSysRoleExists2(oldRole,newRole);
        if(sysRole!=null)
            return true;
        else
            return false;
    }

    @Transactional
    @Override
    public boolean deleteAllByRoleIdIn(List<Integer> roleIdList) {
        try {
            for(Integer roleId:roleIdList)
            {
                roleRepository.deleteRolePermission(roleId);
            }
            roleRepository.deleteAllByRoleIdList(roleIdList);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ISysRolePermission> findSysRolePermissionByRoleId(Integer roleId) {
        return roleRepository.findSysRolePermissionByRoleId(roleId);
    }

    @Transactional
    @Override
    public void grantAuthorization(Integer roleId, List<Integer> permissionList) {
        roleRepository.deleteRolePermission(roleId);
        for(Integer permissionId:permissionList)
        {
            roleRepository.insertRolePermission(roleId,permissionId);
        }
    }

    @Transactional
    @Override
    public void clearAuthorization(Integer roleId) {
        roleRepository.deleteRolePermission(roleId);
    }
}
