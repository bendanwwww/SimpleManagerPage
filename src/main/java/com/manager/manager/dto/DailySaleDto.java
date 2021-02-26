package com.manager.manager.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/14 11:05
 */
@Data
public class DailySaleDto {
    /** id */
    private Integer id;
    /** 职工id */
    private int workerId;
    /** 职工系统姓名 */
    private String workerName;
    /** 单价 */
    private double unitPrice;
    /** 日销量 */
    private double saleAmount;
    /** 利润 */
    private double profile;
    /** 钢材类型 */
    private Integer steelType;
    /** 业绩时间 */
    private Date recordDate;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 客户名称 */
    private String clientName;
    /** 是否是新客户 */
    private String isNewClient;
    /** 备注 */
    private String remark;
    /**
     * 当前页
     */
    private int page;

    /**
     * 每页大小
     */
    private int perPage;
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
     * 获取起始行数
     *
     * @return
     */
    public int getStartRow() {
        return perPage * (page - 1);
    }
    /**
     * 员工id列表
     */
    private List<Integer> workerIdList;
    /** 删除标识 (0: 正常 1: 删除) */
    private int deleteFlag;

}
