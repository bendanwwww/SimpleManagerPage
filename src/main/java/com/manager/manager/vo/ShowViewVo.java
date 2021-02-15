package com.manager.manager.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: mengwenyi
 * @Date: 2021/2/15 14:01
 */
public class ShowViewVo {
    private Map title;
    private Map tooltip;
    private Map legend;
    private Map xAxis;
    private Map yAxis;
    private List<Map> series;

    public Map getTitle() {
        return title;
    }

    public void setTitle(Map title) {
        this.title = title;
    }

    public Map getTooltip() {
        return tooltip;
    }

    public void setTooltip(Map tooltip) {
        this.tooltip = tooltip;
    }

    public Map getLegend() {
        return legend;
    }

    public void setLegend(Map legend) {
        this.legend = legend;
    }

    public Map getxAxis() {
        return xAxis;
    }

    public void setxAxis(Map xAxis) {
        this.xAxis = xAxis;
    }

    public Map getyAxis() {
        return yAxis;
    }

    public void setyAxis(Map yAxis) {
        this.yAxis = yAxis;
    }

    public List<Map> getSeries() {
        return series;
    }

    public void setSeries(List<Map> series) {
        this.series = series;
    }
}
