package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.Area;

/**
 * Area service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface DictAreaService {

    /**
     * Find area list
     * 
     * @return
     */
    public List<Area> findList();

    /**
     * Find by pid
     * 
     * @param pId
     * @return
     */
    public List<Area> findByPId(Long pId);

    /**
     * Save
     * 
     * @param area
     * @return
     */
    public Boolean save(Area area);

    /**
     * Modify
     * 
     * @param area
     * @return
     */
    public Boolean modify(Area area);

    /**
     * Delete
     * 
     * @param id
     * @return
     */
    public Boolean remove(Long id);
}
