package com.crop.seagulls.entities;

import java.sql.Timestamp;
import java.util.List;

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

    private String imgUrl;

    private Timestamp createTime;
    
    
    private List<Varieties> varieties;

    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Category(Long id, Long id2, String enName, String zhName, String firstLetter, String imgUrl) {
        super();
        this.id = id;
        this.pId = id2;
        this.enName = enName;
        this.zhName = zhName;
        this.firstLetter = firstLetter;
        this.imgUrl = imgUrl;
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

    public Long getParentId() {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Varieties> getVarieties() {
        return varieties;
    }

    public void setVarieties(List<Varieties> varieties) {
        this.varieties = varieties;
    }

}
