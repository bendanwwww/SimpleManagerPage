package com.manager.manager.vo;

/**
 * 城市信息返回实体
 *
 * @author lsy
 * Created on 2021-01-25
 */
public class CityInfoVO {

    /** id */
    private int id;
    /** id */
    private int id2;
    /** 城市区号 */
    private String cityCode;
    /** 城市名称 */
    private String cityName;

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
