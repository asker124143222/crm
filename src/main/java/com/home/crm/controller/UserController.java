package com.home.crm.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.crm.entity.User;
import com.home.crm.model.IUserRole;
import com.home.crm.service.UserService;
import com.home.crm.utils.EncryptUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 15:54
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private String algorithmName = "md5";
    private int hashIterations = 2;
    private String salt = "8d78869f470951332959580424d4bf4f";

    @Resource
    UserService userService;
    /**
     * 用户查询.
     * @return
     */
    @RequestMapping("/ulist")
    public String userList(){
        return "/user/userList";
    }

    @RequestMapping(value="/getUserList")
    @RequiresPermissions("user:view")//权限管理;
    @ResponseBody
    public Object getUserList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
    {
        int pageSize = 10;
        try {
            pageSize =  Integer.parseInt(request.getParameter("pageSize"));
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        int pageNumber=0 ;
        try {
            pageNumber =  Integer.parseInt(request.getParameter("pageNumber"))-1;
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        String strUserName=request.getParameter("searchText")==null ? "": request.getParameter("searchText");

        String sortName=request.getParameter("sortName")==null ? "roleId": request.getParameter("sortName");
        String sortOrder=request.getParameter("sortOrder")==null ? "asc": request.getParameter("sortOrder");

        Sort sortLocal = new Sort(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC: Sort.Direction.DESC,sortName);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortLocal);
        Page<User> userPage = userService.findAllByUserNameContains(strUserName,pageable);
        map.put("total",userPage.getTotalElements());
        map.put("rows",userPage.getContent());


        ObjectMapper mapper=new ObjectMapper();
        String jsonString="";
        try {
            jsonString=mapper.writeValueAsString(map);
//            System.out.print(jsonString);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;

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
    @ResponseBody
    public String save(@Valid User user, BindingResult bindingResult,String password2){
        if(bindingResult.hasErrors())
        {
            return "0";
        }
        if(user.getCreateTime()==null)
            user.setCreateTime(LocalDateTime.now());
        user.setSalt(this.salt);
        if(user.getUserId()==null) {
            String encryptPwd = new EncryptUtils(user.getCredentialsSalt(), this.algorithmName, this.hashIterations).encrypt(user.getPassword());
            user.setPassword(encryptPwd);
        }
        else {
            if(!user.getPassword().equals(password2))
            {
                String encryptPwd = new EncryptUtils(user.getCredentialsSalt(), this.algorithmName, this.hashIterations).encrypt(user.getPassword());
                user.setPassword(encryptPwd);
            }
        }
        try {
            userService.save(user);
            return "/user/ulist";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "0";
        }
    }

    @RequestMapping("/checkUserExists")
    @ResponseBody
    public Object checkRoleExists(@RequestParam String newUserName, @RequestParam(required = false) Integer userId, @RequestParam(required = false) String oldUserName)
    {
        Map<String,Boolean> map = new HashMap<>();
        if(userId==null)
        {
            boolean result = !userService.checkUserExists(newUserName);
            map.put("valid",result);
        }
        else
        {
            boolean result = !userService.checkUserExists2(oldUserName,newUserName);
            map.put("valid",result);
        }
        return map;
    }


    @RequestMapping(value = "/edit/{id}")
    @RequiresPermissions("user:add")
    public String edit(@PathVariable("id")Integer id, Map<String,Object> map)
    {
        User user = userService.findUserById(id).orElse(new User());
        map.put("user",user);
        return "/user/userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("user:del")//权限管理;
    public Object userDel(@RequestParam String userIdList){
        if(userIdList==null || userIdList.isEmpty())
        {
            return "0";
        }
        String[] sList = userIdList.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s:sList )
        {
            if(s.equalsIgnoreCase("1"))
                return "0";
            idList.add(Integer.parseInt(s));

        }
        Map<String,String> map = new HashMap<>();
        try {
            userService.deleteAllUserByUserIdList(idList);
            map.put("success","true");
            map.put("url","/user/ulist");
        }catch (Exception e)
        {
            e.printStackTrace();
            map.put("error","true");
        }

        return map;
    }

    @RequestMapping(value="/toListUserRole/{userId}")
    public String listUserRole(@PathVariable("userId")Integer userId,Map<String, Object> map)
    {
        User user = userService.findUserById(userId).orElse(new User());
        map.put("user",user);
        return "/user/userRole";
    }


    @RequestMapping(value = "/toGetUserRole/{userId}")
    @ResponseBody
    public Object getUserRole(@PathVariable("userId")Integer userId,Map<String, Object> map)
    {
        if(userId==null)
            return null;

        List<IUserRole> list = userService.findAllUserRoleByUserId(userId);
        ObjectMapper mapper=new ObjectMapper();
        String jsonString="";
        try {
            jsonString=mapper.writeValueAsString(list);
//            System.out.print(jsonString);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }




    @RequestMapping(value="/toGrantUserRole")
    @ResponseBody
    public Object grantUserRole(Integer userId, String roleIdList)
    {
        if(userId==1) return 0;
        Map<String,String> map = new HashMap<>();
        if(roleIdList==null || roleIdList.isEmpty())
        {
            try {
                userService.deleteAllUserRoleByUserId(userId);
                map.put("success","true");
                map.put("url","/user/ulist");
                return map;
            }catch (Exception e)
            {
                e.printStackTrace();
                map.put("sucess","false");
                map.put("url","/user/ulist");
                return map;
            }
        }
        String[] sList = roleIdList.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s:sList )
        {
            idList.add(Integer.parseInt(s));
        }

        try {
            userService.grantUserRole(userId,idList);
            map.put("sucess","true");
            map.put("url","/user/ulist");

            return map;
        }catch (Exception e)
        {
            e.printStackTrace();
            map.put("sucess","false");
            map.put("url","/user/ulist");
            return map;
        }
    }


}
