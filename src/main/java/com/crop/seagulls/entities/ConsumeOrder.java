package com.crop.seagulls.entities;

import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

public class ConsumeOrder extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -2833603378433113543L;

    private String id;

    private Long userId;

    private Integer payType;

    private Long innerOrderId;

    private Integer source;

    private Timestamp createTime;

    private Timestamp payTime;

    private Timestamp updateTime;

    private Integer status;

    private Integer price;

    private String ip;

    private String metaIndex1;

    private String metaIndex2;

    private String metaIndex3;

    private String metaIndex4;

    private String metaIndex5;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

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

    public Long getInnerOrderId() {
        return innerOrderId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((innerOrderId == null) ? 0 : innerOrderId.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((metaIndex1 == null) ? 0 : metaIndex1.hashCode());
        result = prime * result + ((metaIndex2 == null) ? 0 : metaIndex2.hashCode());
        result = prime * result + ((metaIndex3 == null) ? 0 : metaIndex3.hashCode());
        result = prime * result + ((metaIndex4 == null) ? 0 : metaIndex4.hashCode());
        result = prime * result + ((metaIndex5 == null) ? 0 : metaIndex5.hashCode());
        result = prime * result + ((payType == null) ? 0 : payType.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConsumeOrder other = (ConsumeOrder) obj;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (innerOrderId == null) {
            if (other.innerOrderId != null)
                return false;
        } else if (!innerOrderId.equals(other.innerOrderId))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (metaIndex1 == null) {
            if (other.metaIndex1 != null)
                return false;
        } else if (!metaIndex1.equals(other.metaIndex1))
            return false;
        if (metaIndex2 == null) {
            if (other.metaIndex2 != null)
                return false;
        } else if (!metaIndex2.equals(other.metaIndex2))
            return false;
        if (metaIndex3 == null) {
            if (other.metaIndex3 != null)
                return false;
        } else if (!metaIndex3.equals(other.metaIndex3))
            return false;
        if (metaIndex4 == null) {
            if (other.metaIndex4 != null)
                return false;
        } else if (!metaIndex4.equals(other.metaIndex4))
            return false;
        if (metaIndex5 == null) {
            if (other.metaIndex5 != null)
                return false;
        } else if (!metaIndex5.equals(other.metaIndex5))
            return false;
        if (payType == null) {
            if (other.payType != null)
                return false;
        } else if (!payType.equals(other.payType))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    public void setInnerOrderId(Long innerOrderId) {
        this.innerOrderId = innerOrderId;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", userId=" + userId + ", payType=" + payType + ", innerOrderId=" + innerOrderId + ", createTime=" + createTime + ", updateTime=" + updateTime + ", status="
                + status + ", price=" + price + ", ip=" + ip + ", metaIndex1=" + metaIndex1 + ", metaIndex2=" + metaIndex2 + ", metaIndex3=" + metaIndex3 + ", metaIndex4=" + metaIndex4
                + ", metaIndex5=" + metaIndex5 + "]";
    }

}
