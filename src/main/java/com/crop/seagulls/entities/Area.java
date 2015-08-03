package com.crop.seagulls.entities;

/**
 * Area
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Area {

    private Long id;

    private Long pId;

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
        this.pId = id2;
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

}
