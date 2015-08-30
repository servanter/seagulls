package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
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
    public Response add(Category category);
    
    public Response remove(Long id); 

    /**
     * Modify
     * 
     * @param area
     * @return
     */
    public Response modify(Category category);

    public Paging<Category> findByCategory(Category category);

    public Response findById(Integer id);

}
