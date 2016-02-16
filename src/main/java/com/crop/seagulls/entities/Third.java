package com.crop.seagulls.entities;

import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

public class Third extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -5112785775933057229L;

    protected Long id;

    /**
     * 来源
     */
    protected Integer src;

    protected Long userId;

    protected Timestamp createTime;

    protected Timestamp modifyTime;

    protected Timestamp endTime;

    protected String thirdUnionId;

    protected String metaIndex1;

    protected String metaIndex2;

    protected String metaIndex3;

    protected String metaIndex4;

    protected String metaIndex5;

    public Third() {

    }

    public Third(Integer src) {
        this.src = src;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getMetaIndex1() {
        return metaIndex1;
    }

    public void setMetaIndex1(String metaIndex1) {
        this.metaIndex1 = metaIndex1;
    }

    public String getThirdUnionId() {
        return thirdUnionId;
    }

    public void setThirdUnionId(String thirdUnionId) {
        this.thirdUnionId = thirdUnionId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
