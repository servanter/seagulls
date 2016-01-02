package com.crop.seagulls.entities;

import java.sql.Timestamp;
import java.util.Date;

import com.crop.seagulls.bean.Page;

public class Company extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -3810903359341760723L;

    private Long id;

    private String title;

    private String legalName;

    private String organizationCode;

    private String imgLicense;

    private String imgOrganization;

    private String imgTax;

    private Long userId;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Long auditId;

    private Date auditTime;

    private String userName;
    private String auditName;

    public Company() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Company(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getImgLicense() {
        return imgLicense;
    }

    public void setImgLicense(String imgLicense) {
        this.imgLicense = imgLicense;
    }

    public String getImgOrganization() {
        return imgOrganization;
    }

    public void setImgOrganization(String imgOrganization) {
        this.imgOrganization = imgOrganization;
    }

    public String getImgTax() {
        return imgTax;
    }

    public void setImgTax(String imgTax) {
        this.imgTax = imgTax;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Company [id=" + id + ", title=" + title + ", legalName=" + legalName + ", organizationCode=" + organizationCode + ", imgLicense=" + imgLicense + ", imgOrganization=" + imgOrganization
                + ", imgTax=" + imgTax + ", userId=" + userId + ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", auditId=" + auditId + ", auditTime="
                + auditTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auditId == null) ? 0 : auditId.hashCode());
        result = prime * result + ((auditTime == null) ? 0 : auditTime.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((imgLicense == null) ? 0 : imgLicense.hashCode());
        result = prime * result + ((imgOrganization == null) ? 0 : imgOrganization.hashCode());
        result = prime * result + ((imgTax == null) ? 0 : imgTax.hashCode());
        result = prime * result + ((legalName == null) ? 0 : legalName.hashCode());
        result = prime * result + ((organizationCode == null) ? 0 : organizationCode.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Company other = (Company) obj;
        if (auditId == null) {
            if (other.auditId != null)
                return false;
        } else if (!auditId.equals(other.auditId))
            return false;
        if (auditTime == null) {
            if (other.auditTime != null)
                return false;
        } else if (!auditTime.equals(other.auditTime))
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
        if (imgLicense == null) {
            if (other.imgLicense != null)
                return false;
        } else if (!imgLicense.equals(other.imgLicense))
            return false;
        if (imgOrganization == null) {
            if (other.imgOrganization != null)
                return false;
        } else if (!imgOrganization.equals(other.imgOrganization))
            return false;
        if (imgTax == null) {
            if (other.imgTax != null)
                return false;
        } else if (!imgTax.equals(other.imgTax))
            return false;
        if (legalName == null) {
            if (other.legalName != null)
                return false;
        } else if (!legalName.equals(other.legalName))
            return false;
        if (organizationCode == null) {
            if (other.organizationCode != null)
                return false;
        } else if (!organizationCode.equals(other.organizationCode))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
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
