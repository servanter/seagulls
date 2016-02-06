package com.crop.seagulls.entities;


import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

public class Order extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -2833603378433113543L;

    private String id;

    private Long userId;

    private Integer payType;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer status;

    private Integer price;

    private String ip;

    private String metaIndex1;

    private String metaIndex2;

    private String metaIndex3;

    private String metaIndex4;

    private String metaIndex5;

    public String getMetaIndex1() {
        return metaIndex1;
    }

    public void setMetaIndex1(String metaIndex1) {
        this.metaIndex1 = metaIndex1;
    }

    public String getMetaIndex2() {
        return metaIndex2;
    }

    public void setMetaIndex2(String metaIndex2) {
        this.metaIndex2 = metaIndex2;
    }

    public String getMetaIndex3() {
        return metaIndex3;
    }

    public void setMetaIndex3(String metaIndex3) {
        this.metaIndex3 = metaIndex3;
    }

    public String getMetaIndex4() {
        return metaIndex4;
    }

    public void setMetaIndex4(String metaIndex4) {
        this.metaIndex4 = metaIndex4;
    }

    public String getMetaIndex5() {
        return metaIndex5;
    }

    public void setMetaIndex5(String metaIndex5) {
        this.metaIndex5 = metaIndex5;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", userId=" + userId + ", payType=" + payType + ", createTime=" + createTime + ", updateTime=" + updateTime + ", status=" + status + ", price=" + price + ", ip="
                + ip + ", metaIndex1=" + metaIndex1 + ", metaIndex2=" + metaIndex2 + ", metaIndex3=" + metaIndex3 + ", metaIndex4=" + metaIndex4 + ", metaIndex5=" + metaIndex5 + "]";
    }

}
