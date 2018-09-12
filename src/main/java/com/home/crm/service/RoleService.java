package com.home.crm.service;

import com.home.crm.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/9/5 22:09
 * @Description:
 */
public interface RoleService {
    Page<SysRole> findAll(Pageable pageable);

    Page<SysRole> findAllByRoleContains(String role, Pageable pageable);

    Optional<SysRole> findById(Integer roleId);

    SysRole save(SysRole sysRole);
}
