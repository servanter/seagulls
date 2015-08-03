package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.Category;

/**
 * Categroy service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface DictCategoryService {

    /**
     * Get category list
     * 
     * @return
     */
    public List<Category> findList();

    /**
     * Find by pid
     * 
     * @param pId
     * @return
     */
    public List<Category> findByPId(Long pId);

    /**
     * Save
     * 
     * @param area
     * @return
     */
    public Boolean save(Category category);

    /**
     * Modify
     * 
     * @param area
     * @return
     */
    public Boolean modify(Category category);

}
