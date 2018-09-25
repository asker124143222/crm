package com.home.crm.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.crm.entity.User;
import com.home.crm.service.UserService;
import com.home.crm.utils.EncryptUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    public String save(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return "0";
        }
        if(user.getCreateTime()==null)
            user.setCreateTime(LocalDateTime.now());
        if(user.getUserId()!=null) {
            String encryptPwd = new EncryptUtils(user.getCredentialsSalt(), this.algorithmName, this.hashIterations).encrypt(user.getPassword());
            user.setPassword(encryptPwd);
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
