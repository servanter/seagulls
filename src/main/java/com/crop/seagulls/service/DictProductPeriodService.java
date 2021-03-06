package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.entities.ProductPeriod;

/**
 * Product period service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface DictProductPeriodService {

    public List<ProductPeriod> findPeriods();
}
