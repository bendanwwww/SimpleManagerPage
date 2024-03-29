package com.manager.manager.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/14 21:03
 */
@Data
public class ShowViewDto {
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 员工名称
     */
    private String workerName;
    /**
     * 当前页
     */
    private int page;

    /**
     * 每页大小
     */
    private int perPage;
    /** 是否当天 */
    private Integer isShowNow;
    /** 是否当月 */
    private Integer isShowNowMonth;
    /**
     * 月份
     */
    private String month;
    /**
     * 城市id
     */
    private Integer cityId;
}
