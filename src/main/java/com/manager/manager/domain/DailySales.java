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
    private int id;
    /** 职工id */
    private int workerId;
    /** 职工系统姓名 */
    private String workerName;
    /** 日利润 */
    private double dailyProfit;
    /** 日销量 */
    private double dailyAmount;
    /** 业绩时间 */
    private Date recordDate;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

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

    public double getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(double dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public double getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(double dailyAmount) {
        this.dailyAmount = dailyAmount;
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
}
