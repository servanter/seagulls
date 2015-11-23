package com.crop.seagulls.service;

import java.util.List;
import java.util.Map;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Sell;

/**
 * Supply Service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface SellService {

    /**
     * Add supply
     * 
     * @param sell
     * @param webImagesPath
     * @return
     */
    public Response add(Sell sell, List<String> webImagesPath);

    /**
     * Query suppies
     * 
     * @param supply
     *            search model
     * @return
     */
    public Map<String, Object> findList(Sell sell);
    
    /**
     * Modify supply
     * 
     * @param supply
     * @return
     */
    public Response modify(Sell sell);
    
    
    public Map<String, Object> findById(Sell sell);

    public Map<String, Object> findByUserId(Sell sell);

    public int findCount(Sell sell);

    public List<Category> findHotCategories();
}
