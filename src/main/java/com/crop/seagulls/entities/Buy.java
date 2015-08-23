package com.crop.seagulls.entities;

import java.sql.Timestamp;
import java.util.Date;

import com.crop.seagulls.bean.Page;

/**
 * Buy Info
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Buy extends Page {

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

    private Double quantity;

    private Long buyUnitId;

    private Double startPrice;

    private Double endPrice;

    private Long unitId;

    private String note;

    private String contactName;

    private String contactPhone;

    private Date createTime;

    private Long createUserId;

    private Timestamp updateTime;

    private Date auditTime;

    private Long auditId;

    private Integer status;

    private Long searchCategoryId;
    private Category searchCategory;
    private Integer searchOrderBy;
    private Category searchCategory1;
    private Category searchCategory2;
    private Category searchCategory3;
    private ProductPeriod searchStartTime;
    private Area searchArea;

    private Long addCategoryId;

    private String pagePrice;
    private String pageTimeAlias;
    private Category pageCategory;
    private ProductPeriod pageStartPeriod;
    private ProductPeriod pageEndPeriod;
    private ProductUnit pageBuyUnit;
    private ProductUnit pageUnit;
    private String pageOriginAddr;
    private String pageQuantity;

    public Buy() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Buy(Long id, Long categoryId1, Long categoryId2, Long categoryId3, Long varietiesId, String title, Long provinceId, Long cityId, Long areaId, Long startTime, Long endTime, Double quantity,
            Long buyUnitId, Double startPrice, Double endPrice, Long unitId, String note, String contactName, String contactPhone, Date createTime, Long createUserId, Timestamp updateTime,
            Date auditTime, Long auditId, Integer status) {
        super();
        this.id = id;
        this.categoryId1 = categoryId1;
        this.categoryId2 = categoryId2;
        this.categoryId3 = categoryId3;
        this.varietiesId = varietiesId;
        this.title = title;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.quantity = quantity;
        this.buyUnitId = buyUnitId;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.unitId = unitId;
        this.note = note;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.createTime = createTime;
        this.createUserId = createUserId;
        this.updateTime = updateTime;
        this.auditTime = auditTime;
        this.auditId = auditId;
        this.status = status;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
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

    public Integer getSearchOrderBy() {
        return searchOrderBy;
    }

    public void setSearchOrderBy(Integer searchOrderBy) {
        this.searchOrderBy = searchOrderBy;
    }

    public Category getSearchCategory1() {
        return searchCategory1;
    }

    public void setSearchCategory1(Category searchCategory1) {
        this.searchCategory1 = searchCategory1;
    }

    public Category getSearchCategory2() {
        return searchCategory2;
    }

    public void setSearchCategory2(Category searchCategory2) {
        this.searchCategory2 = searchCategory2;
    }

    public Category getSearchCategory3() {
        return searchCategory3;
    }

    public void setSearchCategory3(Category searchCategory3) {
        this.searchCategory3 = searchCategory3;
    }

    public Area getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Area searchArea) {
        this.searchArea = searchArea;
    }

    public String getPagePrice() {
        return pagePrice;
    }

    public void setPagePrice(String pagePrice) {
        this.pagePrice = pagePrice;
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

    public ProductPeriod getPageStartPeriod() {
        return pageStartPeriod;
    }

    public void setPageStartPeriod(ProductPeriod pageStartPeriod) {
        this.pageStartPeriod = pageStartPeriod;
    }

    public ProductPeriod getPageEndPeriod() {
        return pageEndPeriod;
    }

    public void setPageEndPeriod(ProductPeriod pageEndPeriod) {
        this.pageEndPeriod = pageEndPeriod;
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
        result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
        result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((createUserId == null) ? 0 : createUserId.hashCode());
        result = prime * result + ((endPrice == null) ? 0 : endPrice.hashCode());
        result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((note == null) ? 0 : note.hashCode());
        result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((startPrice == null) ? 0 : startPrice.hashCode());
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
        final Buy other = (Buy) obj;
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
        if (endPrice == null) {
            if (other.endPrice != null)
                return false;
        } else if (!endPrice.equals(other.endPrice))
            return false;
        if (endTime == null) {
            if (other.endTime != null)
                return false;
        } else if (!endTime.equals(other.endTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (note == null) {
            if (other.note != null)
                return false;
        } else if (!note.equals(other.note))
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
        if (startPrice == null) {
            if (other.startPrice != null)
                return false;
        } else if (!startPrice.equals(other.startPrice))
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

    public ProductPeriod getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(ProductPeriod searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public Long getBuyUnitId() {
        return buyUnitId;
    }

    public void setBuyUnitId(Long buyUnitId) {
        this.buyUnitId = buyUnitId;
    }

    public ProductUnit getPageBuyUnit() {
        return pageBuyUnit;
    }

    public void setPageBuyUnit(ProductUnit pageBuyUnit) {
        this.pageBuyUnit = pageBuyUnit;
    }

    public Long getAddCategoryId() {
        return addCategoryId;
    }

    public void setAddCategoryId(Long addCategoryId) {
        this.addCategoryId = addCategoryId;
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

}
