package com.manager.manager.domain;

import java.util.Date;

/**
 * 用户日志表
 *
 * @author lsy
 * Created on 2021-01-25
 */
public class UserLog {

    /** id */
    private String id;
    /** 请求路径 */
    private String url;
    /** 参数 */
    private String params;
    /** 操作人 */
    private String userName;
    /** 操作时间 */
    private Date createTime;

    public UserLog() {}

    public UserLog(String id, String url, String params, String userName, Date createTime) {
        this.id = id;
        this.url = url;
        this.params = params;
        this.userName = userName;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
