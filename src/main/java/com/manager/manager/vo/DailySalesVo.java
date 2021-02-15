package com.manager.manager.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/15 17:46
 */
@Data
public class DailySalesVo {
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
    /** 钢材类型 */
    private String steelTypeName;
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
}
