package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.ProductUnit;

/**
 * Product unit
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface DictProductUnitService {

    /**
     * Find all products
     * 
     * 
     * @return
     */
    public List<ProductUnit> findProductUnits();
}
