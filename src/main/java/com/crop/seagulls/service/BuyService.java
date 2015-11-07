package com.crop.seagulls.service;

import java.util.Map;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Buy;

/**
 * Buy service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface BuyService {

    /**
     * Add buy
     * 
     * @param buy
     * @return
     */
    public Response add(Buy buy);

    /**
     * Modify buy
     * 
     * @param buy
     * @return
     */
    public Response modify(Buy buy);

    /**
     * Find list
     * 
     * @param buy
     * @return
     */
    public Map<String, Object> findList(Buy buy);

    public Map<String, Object> findById(Long id);

    public Map<String, Object> findByUserId(Buy buy);
}
