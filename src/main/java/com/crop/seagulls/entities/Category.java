package com.crop.seagulls.entities;

import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

/**
 * Crop categories
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Category extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -5617806367715474492L;

    private Long id;

    private Long pId;

    private String enName;

    private String zhName;

    private String firstLetter;

    private Timestamp createTime;

    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Category(Long id, Long id2, String enName, String zhName, String firstLetter) {
        super();
        this.id = id;
        pId = id2;
        this.enName = enName;
        this.zhName = zhName;
        this.firstLetter = firstLetter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPId() {
        return pId;
    }

    public void setPId(Long id) {
        pId = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((enName == null) ? 0 : enName.hashCode());
        result = prime * result + ((firstLetter == null) ? 0 : firstLetter.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((pId == null) ? 0 : pId.hashCode());
        result = prime * result + ((zhName == null) ? 0 : zhName.hashCode());
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
        final Category other = (Category) obj;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (enName == null) {
            if (other.enName != null)
                return false;
        } else if (!enName.equals(other.enName))
            return false;
        if (firstLetter == null) {
            if (other.firstLetter != null)
                return false;
        } else if (!firstLetter.equals(other.firstLetter))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (pId == null) {
            if (other.pId != null)
                return false;
        } else if (!pId.equals(other.pId))
            return false;
        if (zhName == null) {
            if (other.zhName != null)
                return false;
        } else if (!zhName.equals(other.zhName))
            return false;
        return true;
    }

}
