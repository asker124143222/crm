package com.home.crm.service;


import com.home.crm.entity.User;
import com.home.crm.model.ISysPermission;
import com.home.crm.model.IUserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 16:45
 * @Description:
 */

public interface UserService {
    User findByUserName(String userName);

    Optional<User> findUserById(Integer userId);

    User save(User user);

    boolean checkUserExists(String userName);
    boolean checkUserExists2(String oldUserName,String newUserName);

    List<IUserRole> findUserRoleByUserName(String userName);

    List<IUserRole> findAllUserRoleByUserId(Integer userId);

    List<ISysPermission> findUserRolePermissionByUserName(String userName);

    Page<User> findAllByUserNameContains(String userName, Pageable pageable);

    void deleteAllUserByUserIdList(List<Integer> userIdList);

    void deleteAllUserRoleByUserIdList(List<Integer> userIdList);

    void deleteAllUserRoleByUserId(Integer userId);

    void grantUserRole(Integer userId,List<Integer> roleIdList);
    
}
