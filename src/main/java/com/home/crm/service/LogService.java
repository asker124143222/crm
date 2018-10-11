package com.home.crm.service;

import com.home.crm.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xu.dm
 * @Date: 2018/10/11 0:01
 * @Description:
 */
public interface LogService {
    void writeLog(String action,String event);
    void save(SysLog sysLog);
    Page<SysLog> findAll(Pageable pageable);
}
