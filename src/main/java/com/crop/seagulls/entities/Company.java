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

}
