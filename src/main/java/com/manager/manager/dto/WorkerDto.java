package com.manager.manager.dto;

import com.manager.manager.domain.Worker;

import java.util.Date;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/7 17:02
 */
public class WorkerDto {
    /** id */
    private Integer id;
    /** 真实姓名 */
    private String realName;
    /** 系统姓名 */
    private String sysName;
    /** 员工微信号 */
    private String wxCard;
    /** 性别 (0: 男 1: 女) */
    private int sex;
    /** 年龄 */
    private int age;
    /** 工龄 */
    private int workAge;
    /** 城市编码 */
    private String cityCode;
    /** 城市名称 */
    private String cityName;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 删除标识 (0: 正常 1: 删除) */
    private int deleteFlag;

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页大小
     */
    private int perPage;
    /**
     * 获取起始行数
     *
     * @return
     */
    public int getStartRow() {
        return perPage * (page - 1);
    }
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getWxCard() {
        return wxCard;
    }

    public void setWxCard(String wxCard) {
        this.wxCard = wxCard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkAge() {
        return workAge;
    }

    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
