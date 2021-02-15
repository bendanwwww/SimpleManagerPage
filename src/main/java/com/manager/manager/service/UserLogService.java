package com.manager.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.manager.domain.UserLog;
import com.manager.manager.mapper.UserLogMapper;

/**
 * 城市相关service
 *
 * @author lsy
 * Created on 2021-01-25
 */
@Service
public class UserLogService {

    @Autowired
    private UserLogMapper userLogMapper;

    /**
     * 添加日志
     * @param userLog
     */
    public void insertLog(UserLog userLog) {
        userLogMapper.insertLog(userLog);
    }

}
