package com.home.crm.repository;

import com.home.crm.entity.SysRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @Author: xu.dm
 * @Date: 2018/9/5 21:57
 * @Description:
 */
public interface RoleRepository extends JpaRepository<SysRole,Integer> {
    Page<SysRole> findAllByRoleContains(String role, Pageable pageable);
    SysRole findSysRoleByRole(String role);

    @Query(value="select * from SysRole where role <> ?1 and role = ?2",nativeQuery = true)
    SysRole findSysRoleExists2(String oldRole,String newRole);

    @Transactional
    @Modifying
    @Query(value = "delete from SysRole where roleId in (?1)",nativeQuery = true)
    void deleteAllByRoleIdList(List<Integer> roleIdList);
}
