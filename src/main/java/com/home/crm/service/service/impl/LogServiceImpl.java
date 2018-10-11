package com.home.crm.service.service.impl;

import com.home.crm.entity.SysLog;
import com.home.crm.repository.SysLogRepository;
import com.home.crm.service.LogService;
import com.home.crm.utils.NetworkUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author: xu.dm
 * @Date: 2018/10/11 18:42
 * @Description:
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    SysLogRepository sysLogRepository;

    @Override
    public void writeLog(String action,String event)
    {
        //在异步里或者子线程里无法获取RequestContextHolder.getRequestAttributes()
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysLog sysLog = new SysLog();
        sysLog.setAction(action);
        sysLog.setEvent(event);
        sysLog.setHost(NetworkUtils.getIpAddress(request));
        sysLog.setUserName((String)request.getSession().getAttribute("userName"));
        sysLog.setInsertTime(LocalDateTime.now());
        save(sysLog);
    }

    @Async
    @Override
    public void save(SysLog sysLog) {

        sysLogRepository.save(sysLog);
    }

    @Override
    public Page<SysLog> findAll(Pageable pageable) {
        return sysLogRepository.findAll(pageable);
    }

}
