package com.crop.seagulls.service;

import java.util.List;
import java.util.Map;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Supply;

/**
 * Supply Service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface SupplyService {

    /**
     * Add supply
     * 
     * @param supply
     * @param webImagesPath
     * @return
     */
    public Response add(Supply supply, List<String> webImagesPath);

    /**
     * Query suppies
     * 
     * @param supply
     *            search model
     * @return
     */
    public Map<String, Object> querySuppliesWithExt(Supply supply);
    
    /**
     * Modify supply
     * 
     * @param supply
     * @return
     */
    public Response modifySupply(Supply supply);
    
    
    public Map<String, Object> findById(Long id);
}
