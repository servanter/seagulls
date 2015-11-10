package com.crop.seagulls.entities;

import java.sql.Timestamp;
import java.util.Date;

import com.crop.seagulls.bean.Page;

/**
 * Supply Info
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Sell extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = 7034348294179056770L;

    private Long id;

    private Long categoryId1;

    private Long categoryId2;

    private Long categoryId3;

    private Long varietiesId;

    private String title;

    private Long provinceId;

    private Long cityId;

    private Long areaId;

    private Long startTime;

    private Long endTime;

    private Double price;

    private Long unitId;

    private String note;

    private String contactName;

    private String contactPhone;

    private String companyName;

    private Long companyId;

    private Boolean isValid;

    private Boolean isPublish;

    private Date createTime;

    private Long createUserId;

    private Timestamp updateTime;

    private Date auditTime;

    private Long auditId;

    private Integer status;

    private Long searchCategoryId;
    private Category searchCategory;

    private String pageTimeAlias;
    private Category pageCategory;
    private ProductUnit pageUnit;
    private String pageAddress;
    private String pagePeriod;

    public Sell() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Long categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Long getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(Long categoryId3) {
        this.categoryId3 = categoryId3;
    }

    public Long getVarietiesId() {
        return varietiesId;
    }

    public void setVarietiesId(Long varietiesId) {
        this.varietiesId = varietiesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSearchCategoryId() {
        return searchCategoryId;
    }

    public void setSearchCategoryId(Long searchCategoryId) {
        this.searchCategoryId = searchCategoryId;
    }

    public String getPageTimeAlias() {
        return pageTimeAlias;
    }

    public void setPageTimeAlias(String pageTimeAlias) {
        this.pageTimeAlias = pageTimeAlias;
    }

    public Category getPageCategory() {
        return pageCategory;
    }

    public void setPageCategory(Category pageCategory) {
        this.pageCategory = pageCategory;
    }

    public ProductUnit getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(ProductUnit pageUnit) {
        this.pageUnit = pageUnit;
    }

    public Category getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(Category searchCategory) {
        this.searchCategory = searchCategory;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getPageAddress() {
        return pageAddress;
    }

    public void setPageAddress(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    public String getPagePeriod() {
        return pagePeriod;
    }

    public void setPagePeriod(String pagePeriod) {
        this.pagePeriod = pagePeriod;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Boolean getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Boolean isPublish) {
        this.isPublish = isPublish;
    }

}
