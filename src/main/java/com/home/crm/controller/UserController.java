package com.home.crm.controller;

import com.home.crm.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 15:54
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:view")//权限管理;
    public String userList(){
        return "/user/userList";
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    @RequiresPermissions("user:add")//权限管理;
    public String toUserAdd(User user){
        return "/user/userAdd";
    }


    /**
     * 用户添加;
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @RequiresPermissions("user:add")//权限管理;
    public String userAdd(){
        return "/user/userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "/user/userDel";
    }
}
