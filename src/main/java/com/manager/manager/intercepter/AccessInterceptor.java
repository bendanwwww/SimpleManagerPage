package com.manager.manager.intercepter;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.manager.manager.domain.UserLog;
import com.manager.manager.service.UserLogService;

/**
 * file: UserInterceptor.java
 */
public class AccessInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

    private UserLogService userLogService;

    public AccessInterceptor(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 对来自后台的请求统一进行日志处理 */
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String userName = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(Objects.nonNull(principal) && principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
            // 记录日志表
            UserLog userLog = new UserLog(UUID.randomUUID().toString().replaceAll("-", ""), uri, queryString, userName, new Date());
            userLogService.insertLog(userLog);
        }

        logger.info(String.format("用户: %s, 请求参数, url: %s, method: %s, uri: %s, params: %s", userName, url, method, uri, queryString));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}