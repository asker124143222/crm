package com.home.crm.repository;

import com.home.crm.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author: xu.dm
 * @Date: 2018/9/5 21:57
 * @Description:
 */
public interface RoleRepository extends JpaRepository<SysRole,Integer> {
    Page<SysRole> findAllByRoleContains(String role, Pageable pageable);
}
