package com.manager.manager.domain;

import java.util.Date;

/**
 * 职工每日业绩表实体
 *
 * @author lsy
 * Created on 2021-01-25
 */
public class DailySales {
    /** id */
    private Integer id;
    /** id */
    private Integer id2;
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

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getProfile() {
        return profile;
    }

    public void setProfile(double profile) {
        this.profile = profile;
    }

    public Integer getSteelType() {
        return steelType;
    }

    public void setSteelType(Integer steelType) {
        this.steelType = steelType;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIsNewClient() {
        return isNewClient;
    }

    public void setIsNewClient(String isNewClient) {
        this.isNewClient = isNewClient;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
