package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Category;

@Repository
public interface DictCategoryDAO {

    /**
     * Get category list
     * 
     * @return
     */
    public List<Category> getList();

    /**
     * Get by pId
     * 
     * @param id
     * @return
     */
    public List<Category> getByPId(Long id);

    /**
     * Update
     * 
     * @param category
     * @return
     */
    public int update(Category category);

    /**
     * Insert
     * 
     * @param category
     * @return
     */
    public int insert(Category category);

}
