package com.manager.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.manager.manager.domain.UserLog;

@Mapper
@Repository
public interface UserLogMapper {

    /**
     * 添加日志
     * @param userLog
     */
    void insertLog(UserLog userLog);

}