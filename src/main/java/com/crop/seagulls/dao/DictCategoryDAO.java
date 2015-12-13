package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Category;

@Repository("dictCategoryDAO")
public interface DictCategoryDAO {

    /**
     * Get category list
     * 
     * @return
     */
    public List<Category> getAll();

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

    public List<Category> getByCategory(Category category);

    public int getByCategoryCount(Category category);

    public Category getById(Integer id);

    public int delete(Long id);

    public Long getNextId(@Param("start") Long start, @Param("end") Long end);
}
