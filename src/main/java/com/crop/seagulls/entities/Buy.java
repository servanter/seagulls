package com.crop.seagulls.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.crop.seagulls.bean.Base;

/**
 * Buy Info
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Buy extends Base {

    /**
     * 
     */
    private static final long serialVersionUID = -4427062415314476214L;

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

    private Integer quantity;

    private Double price;

    private Long unitId;

    private String note;

    private String contactName;

    private String contactPhone;

    private String companyName;

    private Long companyId;

    private Boolean isPublish;

    private Boolean isValid;

    private Date createTime;

    private Date refreshTime;

    private Long createUserId;

    private Timestamp updateTime;

    private Date auditTime;

    private Long auditId;

    private Integer status;

    private Long searchCategoryId;
    private Category searchCategory;
    private Integer searchExceptStatus;
    private List<Long> searchIds;

    private String pageTimeAlias;
    private Category pageCategory;
    private ProductUnit pageUnit;
    private String pageOriginAddr;
    private Varieties pageVarieties;
    private String pageAddress;
    private String pageQuantity;
    private String pagePeriod;
    private BuyPic firstPic;
    private User user;
    private com.crop.seagulls.entities.admin.User auditUser;
    private String updatePicIds;

    public String getUpdatePicIds() {
        return updatePicIds;
    }

    public void setUpdatePicIds(String updatePicIds) {
        this.updatePicIds = updatePicIds;
    }

    public Buy() {
        super();
        // TODO Auto-generated constructor stub
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
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

    public String getPageOriginAddr() {
        return pageOriginAddr;
    }

    public void setPageOriginAddr(String pageOriginAddr) {
        this.pageOriginAddr = pageOriginAddr;
    }

    public ProductUnit getPageUnit() {
        return pageUnit;
    }

    public void setPageUnit(ProductUnit pageUnit) {
        this.pageUnit = pageUnit;
    }

    public String getPageQuantity() {
        return pageQuantity;
    }

    public void setPageQuantity(String pageQuantity) {
        this.pageQuantity = pageQuantity;
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

    public Varieties getPageVarieties() {
        return pageVarieties;
    }

    public void setPageVarieties(Varieties pageVarieties) {
        this.pageVarieties = pageVarieties;
    }

    public BuyPic getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(BuyPic firstPic) {
        this.firstPic = firstPic;
    }

    public Integer getSearchExceptStatus() {
        return searchExceptStatus;
    }

    public void setSearchExceptStatus(Integer searchExceptStatus) {
        this.searchExceptStatus = searchExceptStatus;
    }

    public List<Long> getSearchIds() {
        return searchIds;
    }

    public void setSearchIds(List<Long> searchIds) {
        this.searchIds = searchIds;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public com.crop.seagulls.entities.admin.User getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(com.crop.seagulls.entities.admin.User auditUser) {
        this.auditUser = auditUser;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
        result = prime * result + ((auditId == null) ? 0 : auditId.hashCode());
        result = prime * result + ((auditTime == null) ? 0 : auditTime.hashCode());
        result = prime * result + ((categoryId1 == null) ? 0 : categoryId1.hashCode());
        result = prime * result + ((categoryId2 == null) ? 0 : categoryId2.hashCode());
        result = prime * result + ((categoryId3 == null) ? 0 : categoryId3.hashCode());
        result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
        result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((createUserId == null) ? 0 : createUserId.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((firstPic == null) ? 0 : firstPic.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isPublish == null) ? 0 : isPublish.hashCode());
        result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((pageAddress == null) ? 0 : pageAddress.hashCode());
        result = prime * result + ((pageCategory == null) ? 0 : pageCategory.hashCode());
        result = prime * result + ((pageOriginAddr == null) ? 0 : pageOriginAddr.hashCode());
        result = prime * result + ((pagePeriod == null) ? 0 : pagePeriod.hashCode());
        result = prime * result + ((pageQuantity == null) ? 0 : pageQuantity.hashCode());
        result = prime * result + ((pageTimeAlias == null) ? 0 : pageTimeAlias.hashCode());
        result = prime * result + ((pageUnit == null) ? 0 : pageUnit.hashCode());
        result = prime * result + ((pageVarieties == null) ? 0 : pageVarieties.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((refreshTime == null) ? 0 : refreshTime.hashCode());
        result = prime * result + ((searchCategory == null) ? 0 : searchCategory.hashCode());
        result = prime * result + ((searchCategoryId == null) ? 0 : searchCategoryId.hashCode());
        result = prime * result + ((searchExceptStatus == null) ? 0 : searchExceptStatus.hashCode());
        result = prime * result + ((searchIds == null) ? 0 : searchIds.hashCode());
        result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((unitId == null) ? 0 : unitId.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        result = prime * result + ((varietiesId == null) ? 0 : varietiesId.hashCode());
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
        Buy other = (Buy) obj;
        if (areaId == null) {
            if (other.areaId != null)
                return false;
        } else if (!areaId.equals(other.areaId))
            return false;
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
        if (categoryId1 == null) {
            if (other.categoryId1 != null)
                return false;
        } else if (!categoryId1.equals(other.categoryId1))
            return false;
        if (categoryId2 == null) {
            if (other.categoryId2 != null)
                return false;
        } else if (!categoryId2.equals(other.categoryId2))
            return false;
        if (categoryId3 == null) {
            if (other.categoryId3 != null)
                return false;
        } else if (!categoryId3.equals(other.categoryId3))
            return false;
        if (cityId == null) {
            if (other.cityId != null)
                return false;
        } else if (!cityId.equals(other.cityId))
            return false;
        if (companyId == null) {
            if (other.companyId != null)
                return false;
        } else if (!companyId.equals(other.companyId))
            return false;
        if (companyName == null) {
            if (other.companyName != null)
                return false;
        } else if (!companyName.equals(other.companyName))
            return false;
        if (contactName == null) {
            if (other.contactName != null)
                return false;
        } else if (!contactName.equals(other.contactName))
            return false;
        if (contactPhone == null) {
            if (other.contactPhone != null)
                return false;
        } else if (!contactPhone.equals(other.contactPhone))
            return false;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (createUserId == null) {
            if (other.createUserId != null)
                return false;
        } else if (!createUserId.equals(other.createUserId))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (firstPic == null) {
            if (other.firstPic != null)
                return false;
        } else if (!firstPic.equals(other.firstPic))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isPublish == null) {
            if (other.isPublish != null)
                return false;
        } else if (!isPublish.equals(other.isPublish))
            return false;
        if (isValid == null) {
            if (other.isValid != null)
                return false;
        } else if (!isValid.equals(other.isValid))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
            return false;
        if (pageAddress == null) {
            if (other.pageAddress != null)
                return false;
        } else if (!pageAddress.equals(other.pageAddress))
            return false;
        if (pageCategory == null) {
            if (other.pageCategory != null)
                return false;
        } else if (!pageCategory.equals(other.pageCategory))
            return false;
        if (pageOriginAddr == null) {
            if (other.pageOriginAddr != null)
                return false;
        } else if (!pageOriginAddr.equals(other.pageOriginAddr))
            return false;
        if (pagePeriod == null) {
            if (other.pagePeriod != null)
                return false;
        } else if (!pagePeriod.equals(other.pagePeriod))
            return false;
        if (pageQuantity == null) {
            if (other.pageQuantity != null)
                return false;
        } else if (!pageQuantity.equals(other.pageQuantity))
            return false;
        if (pageTimeAlias == null) {
            if (other.pageTimeAlias != null)
                return false;
        } else if (!pageTimeAlias.equals(other.pageTimeAlias))
            return false;
        if (pageUnit == null) {
            if (other.pageUnit != null)
                return false;
        } else if (!pageUnit.equals(other.pageUnit))
            return false;
        if (pageVarieties == null) {
            if (other.pageVarieties != null)
                return false;
        } else if (!pageVarieties.equals(other.pageVarieties))
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
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (refreshTime == null) {
            if (other.refreshTime != null)
                return false;
        } else if (!refreshTime.equals(other.refreshTime))
            return false;
        if (searchCategory == null) {
            if (other.searchCategory != null)
                return false;
        } else if (!searchCategory.equals(other.searchCategory))
            return false;
        if (searchCategoryId == null) {
            if (other.searchCategoryId != null)
                return false;
        } else if (!searchCategoryId.equals(other.searchCategoryId))
            return false;
        if (searchExceptStatus == null) {
            if (other.searchExceptStatus != null)
                return false;
        } else if (!searchExceptStatus.equals(other.searchExceptStatus))
            return false;
        if (searchIds == null) {
            if (other.searchIds != null)
                return false;
        } else if (!searchIds.equals(other.searchIds))
            return false;
        if (startTime == null) {
            if (other.startTime != null)
                return false;
        } else if (!startTime.equals(other.startTime))
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
        if (unitId == null) {
            if (other.unitId != null)
                return false;
        } else if (!unitId.equals(other.unitId))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        if (varietiesId == null) {
            if (other.varietiesId != null)
                return false;
        } else if (!varietiesId.equals(other.varietiesId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Buy [id=" + id + ", categoryId1=" + categoryId1 + ", categoryId2=" + categoryId2 + ", categoryId3=" + categoryId3 + ", varietiesId=" + varietiesId + ", title=" + title
                + ", provinceId=" + provinceId + ", cityId=" + cityId + ", areaId=" + areaId + ", startTime=" + startTime + ", endTime=" + endTime + ", quantity=" + quantity + ", price=" + price
                + ", unitId=" + unitId + ", note=" + note + ", contactName=" + contactName + ", contactPhone=" + contactPhone + ", companyName=" + companyName + ", companyId=" + companyId
                + ", isPublish=" + isPublish + ", isValid=" + isValid + ", createTime=" + createTime + ", refreshTime=" + refreshTime + ", createUserId=" + createUserId + ", updateTime=" + updateTime
                + ", auditTime=" + auditTime + ", auditId=" + auditId + ", status=" + status + ", searchCategoryId=" + searchCategoryId + ", searchCategory=" + searchCategory
                + ", searchExceptStatus=" + searchExceptStatus + ", searchIds=" + searchIds + ", pageTimeAlias=" + pageTimeAlias + ", pageCategory=" + pageCategory + ", pageUnit=" + pageUnit
                + ", pageOriginAddr=" + pageOriginAddr + ", pageVarieties=" + pageVarieties + ", pageAddress=" + pageAddress + ", pageQuantity=" + pageQuantity + ", pagePeriod=" + pagePeriod
                + ", firstPic=" + firstPic + "]";
    }

}
