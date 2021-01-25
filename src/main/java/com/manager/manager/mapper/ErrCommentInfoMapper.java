package com.manager.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
//@Repository
public interface ErrCommentInfoMapper {
    /** 查询学生学科下评价 */
    List<String> queryCommentSubjectList(@Param(value = "studentId") String studentId);
}