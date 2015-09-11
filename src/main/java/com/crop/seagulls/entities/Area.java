package com.crop.seagulls.entities;

import com.crop.seagulls.bean.Page;

/**
 * Area
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Area extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -2795149107075556958L;

    private Long id;

    private Long parentId;

    private String enName;

    private String zhName;

    private String firstLetter;

    public Area() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Area(Long id, Long id2, String enName, String zhName, String firstLetter) {
        super();
        this.id = id;
        this.parentId = id2;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

}
