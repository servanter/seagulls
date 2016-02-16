package com.crop.seagulls.entities;

import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

public class SellProduct extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -2718765185283939829L;

    private Long id;

    private Long userId;

    private Long sellId;

    private Long provinceId;

    private Long cityId;

    private Long areaId;

    private String address;

    private Integer num;

    private Integer price;

    private Integer totalPrice;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SellProduct [id=" + id + ", userId=" + userId + ", sellId=" + sellId + ", provinceId=" + provinceId + ", cityId=" + cityId + ", areaId=" + areaId + ", address=" + address + ", num="
                + num + ", price=" + price + ", totalPrice=" + totalPrice + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
        result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((num == null) ? 0 : num.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
        result = prime * result + ((sellId == null) ? 0 : sellId.hashCode());
        result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
        SellProduct other = (SellProduct) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (areaId == null) {
            if (other.areaId != null)
                return false;
        } else if (!areaId.equals(other.areaId))
            return false;
        if (cityId == null) {
            if (other.cityId != null)
                return false;
        } else if (!cityId.equals(other.cityId))
            return false;
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
        if (num == null) {
            if (other.num != null)
                return false;
        } else if (!num.equals(other.num))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (provinceId == null) {
            if (other.provinceId != null)
                return false;
        } else if (!provinceId.equals(other.provinceId))
            return false;
        if (sellId == null) {
            if (other.sellId != null)
                return false;
        } else if (!sellId.equals(other.sellId))
            return false;
        if (totalPrice == null) {
            if (other.totalPrice != null)
                return false;
        } else if (!totalPrice.equals(other.totalPrice))
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

}
