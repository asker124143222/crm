package com.home.crm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: xu.dm
 * @Date: 2018/8/11 16:09
 * @Description:
 */
@Controller
public class HomeController {
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "/user/403";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(Map<String, Object> map,HttpServletRequest request)
    {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/user/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Map<String, Object> map,HttpServletRequest request) throws Exception{
        System.out.println("login()");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        String msg = "";

        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();

//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//
//        }

        // 3、将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        // 4、认证
        try {
            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
            Session session = currentUser.getSession();
            session.setAttribute("userName", userName);
            return "/index";
        }catch (UnknownAccountException e)
        {
            e.printStackTrace();
            msg = "UnknownAccountException -- > 账号不存在：";
        }
        catch (IncorrectCredentialsException e)
        {
            msg = "IncorrectCredentialsException -- > 密码不正确：";
        }
        catch (AuthenticationException e) {
            e.printStackTrace();
            msg="用户验证失败";
        }
        map.put("msg",msg);
        map.put("userName",userName);
        return "/user/login";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "login";
    }
}
