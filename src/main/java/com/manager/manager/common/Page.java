package com.manager.manager.common;

import com.alibaba.fastjson.JSON;

/**
 * @description:分页对象
 * @author: zhangx
 * @Date: 2018/1/25 20:03
 */
public class Page<T> {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final int DEFAULT_PAGE_NO = 1;

    private static final int MAX_PAGE_SIZE = 1000;

    private static final int MIX_PAGE_SIZE = 1;

    /**
     * 当前页
     */
    private int pageNo = DEFAULT_PAGE_NO;

    /**
     * 每页大小
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 总页数
     */
    private int pageTotalSize;

    /**
     * 总条数
     */
    private int total;

    /**
     * 返回数据
     */
    private T rows;
    /**
     * 是否是es数据 0:数据库,1:Es
     */
    private int isEs = 0;


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo < MIX_PAGE_SIZE ? MIX_PAGE_SIZE : pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获取起始行数
     *
     * @return
     */
    public int getStartRow() {
        return pageSize * (pageNo - 1);
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            this.pageSize = pageSize < 0 ? DEFAULT_PAGE_SIZE : pageSize;
        } else if (pageSize > MAX_PAGE_SIZE) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }

    }


    public int getPageTotalSize() {
        return pageTotalSize;
    }

    public void setPageTotalSize(int pageTotalSize) {
        this.pageTotalSize = pageTotalSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        this.pageTotalSize = (int) Math.ceil(total / ((double) (pageSize)));

    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getIsEs() {
        return isEs;
    }

    public void setIsEs(int isEs) {
        this.isEs = isEs;
    }
}
