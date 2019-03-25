package com.cnbdty.device.model;

import java.util.Date;

public class ObjectPosition {
    private Long id;

    private Long objId;

    private Date gpsTime;

    private Integer gpsType;

    private Double longitude;

    private Double latitude;

    private Double baiduLg;

    private Double baiduLt;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public Date getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(Date gpsTime) {
        this.gpsTime = gpsTime;
    }

    public Integer getGpsType() {
        return gpsType;
    }

    public void setGpsType(Integer gpsType) {
        this.gpsType = gpsType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getBaiduLg() {
        return baiduLg;
    }

    public void setBaiduLg(Double baiduLg) {
        this.baiduLg = baiduLg;
    }

    public Double getBaiduLt() {
        return baiduLt;
    }

    public void setBaiduLt(Double baiduLt) {
        this.baiduLt = baiduLt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}