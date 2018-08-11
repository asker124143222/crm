package com.home.crm.repository;

import com.home.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: xu.dm
 * @Date: 2018/6/10 19:54
 * @Description:
 */

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}
