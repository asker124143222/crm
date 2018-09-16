package com.home.crm.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.crm.entity.SysRole;
import com.home.crm.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
 * @Date: 2018/9/5 22:28
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class RoleController {
    @Resource
    RoleService roleService;

    //,produces="application/json;charset=UTF-8"
    @RequestMapping(value="/role")
    @ResponseBody
    public Object getRole(HttpServletRequest request,HttpServletResponse response, Map<String, Object> map)
    {

        int pageSize = 5;
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


        String strRole=request.getParameter("searchText")==null ? "": request.getParameter("searchText");

        String sortName=request.getParameter("sortName")==null ? "roleId": request.getParameter("sortName");
        String sortOrder=request.getParameter("sortOrder")==null ? "asc": request.getParameter("sortOrder");

        Sort sortLocal = new Sort(sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC: Sort.Direction.DESC,sortName);
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortLocal);
//        Page<SysRole> sysRolePage = roleService.findAll(pageable);
        Page<SysRole> sysRolePage = roleService.findAllByRoleContains(strRole, pageable);
        map.put("total",sysRolePage.getTotalElements());
        map.put("rows",sysRolePage.getContent());

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

    @RequestMapping("/rlist")
    public String list()
    {
        return "/user/roleList";
    }


    @RequestMapping(value="/roleAdd", method= RequestMethod.GET)
//    @RequiresPermissions("role:add")
    public String toAdd(SysRole sysRole) {
//        sysRole.setAvailable(false);
        return "/user/roleAdd";
    }

    @RequestMapping(value="/roleAdd",method = RequestMethod.POST)
    @ResponseBody
    public String save(@Valid SysRole sysRole, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "0";
        }
        if(sysRole.getCreateTime()==null)
            sysRole.setCreateTime(LocalDateTime.now());
        try {
            roleService.save(sysRole);
            return  "/user/rlist";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "0";
        }
    }


    @RequestMapping("/checkRoleExists")
    @ResponseBody
    public Object checkRoleExists(@RequestParam String newRole,@RequestParam(required = false) Integer roleId,@RequestParam(required = false) String oldRole)
    {
        Map<String,Boolean> map = new HashMap<>();
        if(roleId==null)
        {
            boolean result = !roleService.checkRoleExists(newRole);
            map.put("valid",result);
        }
        else
        {
            boolean result = !roleService.checkRoleExists(oldRole,newRole);
            map.put("valid",result);
        }
        return map;
    }


    @RequestMapping(value = "/roleEdit/{id}")
//    @RequiresPermissions("customer:add")
    public String edit(@PathVariable("id")Integer id,Map<String,Object> map)
    {
        SysRole sysRole = roleService.findById(id).orElse(new SysRole());
        map.put("sysRole",sysRole);
        return "/user/roleAdd";
    }
    
    @RequestMapping(value = "/roleDelete")
    @ResponseBody
    public Object delete(@RequestParam String roleIdList)
    {
        if(roleIdList==null || roleIdList.isEmpty())
        {
            return "0";
        }
        String[] sList = roleIdList.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s:sList )
        {
            if(s.equalsIgnoreCase("1"))
                return "0";
            idList.add(Integer.parseInt(s));

        }
        boolean result = roleService.deleteAllByRoleIdIn(idList);
        Map<String,String> map = new HashMap<>();
        if(result)
        {
            map.put("success","true");
            map.put("url","/user/rlist");
        }
        else
        {
            map.put("error","true");
        }

        return map;
    }
}
