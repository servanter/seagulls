package com.crop.seagulls.service;

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
     * @return
     */
    public Response add(Supply supply);

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
    
    
    public Supply findById(Long id);
}
