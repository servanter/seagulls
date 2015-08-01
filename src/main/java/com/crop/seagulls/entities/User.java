package com.crop.seagulls.entities;

import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

/**
 * User entities
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class User extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -4376411297191312406L;

    private Long id;

    private String nickName;

    private String realName;

    private String phone;

    private String password;

    private Long provinceId;

    private Long cityId;

    private Long areaId;

    private String headUrl;

    private Integer identityId;

    private Boolean isValid;

    private Timestamp createTime;

    private Timestamp updateTime;

    public User() {
    }

    public User(Long id, String nickName, String realName, String phone, String password, Long provinceId, Long cityId, Long areaId, String headUrl,
            Integer identityId, Boolean isValid, Timestamp createTime, Timestamp updateTime) {
        super();
        this.id = id;
        this.nickName = nickName;
        this.realName = realName;
        this.phone = phone;
        this.password = password;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
        this.headUrl = headUrl;
        this.identityId = identityId;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
        result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((headUrl == null) ? 0 : headUrl.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((identityId == null) ? 0 : identityId.hashCode());
        result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
        result = prime * result + ((realName == null) ? 0 : realName.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
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
        final User other = (User) obj;
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
        if (headUrl == null) {
            if (other.headUrl != null)
                return false;
        } else if (!headUrl.equals(other.headUrl))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (identityId == null) {
            if (other.identityId != null)
                return false;
        } else if (!identityId.equals(other.identityId))
            return false;
        if (isValid == null) {
            if (other.isValid != null)
                return false;
        } else if (!isValid.equals(other.isValid))
            return false;
        if (nickName == null) {
            if (other.nickName != null)
                return false;
        } else if (!nickName.equals(other.nickName))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (provinceId == null) {
            if (other.provinceId != null)
                return false;
        } else if (!provinceId.equals(other.provinceId))
            return false;
        if (realName == null) {
            if (other.realName != null)
                return false;
        } else if (!realName.equals(other.realName))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", nickName=" + nickName + ", realName=" + realName + ", phone=" + phone + ", password=" + password
                + ", provinceId=" + provinceId + ", cityId=" + cityId + ", areaId=" + areaId + ", headUrl=" + headUrl + ", identityId=" + identityId
                + ", isValid=" + isValid + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }

}
