package com.home.crm.service;


import com.home.crm.entity.User;
import com.home.crm.model.ISysPermission;
import com.home.crm.model.IUserRole;


import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 16:45
 * @Description:
 */

public interface UserService {
    User findByUserName(String userName);

    List<IUserRole> findUserRoleByUserName(String userName);

    List<ISysPermission> findUserRolePermissionByUserName(String userName);
}
