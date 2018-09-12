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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        sysRole.setAvailable(false);
        return "/user/roleAdd";
    }

    @RequestMapping(value="/roleAdd",method = RequestMethod.POST)
    public String save(@Valid SysRole sysRole, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "/user/roleAdd";
        }
        roleService.save(sysRole);
        return "/user/roleList";
    }


}
