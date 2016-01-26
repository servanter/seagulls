package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.ProductPeriod;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.service.DictProductPeriodService;
import com.crop.seagulls.service.DictProductUnitService;
import com.crop.seagulls.util.Logger;

/**
 * Product relation infod
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Component
public class ProductRelationCache {

    public Logger logger = Logger.getLogger(ProductRelationCache.class);

    private static List<ProductUnit> UNITS = new ArrayList<ProductUnit>();

    private static Map<Long, ProductUnit> UNITS_MAP = new HashMap<Long, ProductUnit>();

    private static List<ProductPeriod> PERIODS = new ArrayList<ProductPeriod>();

    private static Map<Long, ProductPeriod> PERIODS_MAP = new HashMap<Long, ProductPeriod>();

    @Autowired
    private DictProductUnitService dictProductUnitService;

    @Autowired
    private DictProductPeriodService dictProductPeriodService;

    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        logger.info("ProductRelationCache start");
        List<ProductUnit> units = dictProductUnitService.findProductUnits();
        List<ProductPeriod> periods = dictProductPeriodService.findPeriods();
        Map<Long, ProductUnit> unitMap = new HashMap<Long, ProductUnit>();
        Map<Long, ProductPeriod> periodMap = new HashMap<Long, ProductPeriod>();
        if (CollectionUtils.isNotEmpty(units)) {
            for (ProductUnit productUnit : units) {
                unitMap.put(productUnit.getId(), productUnit);
            }
        }
        if (CollectionUtils.isNotEmpty(periods)) {
            for (ProductPeriod productPeriod : periods) {
                periodMap.put(productPeriod.getId(), productPeriod);
            }
        }
        UNITS = units;
        PERIODS = periods;
        UNITS_MAP = unitMap;
        PERIODS_MAP = periodMap;
        logger.info("ProductRelationCache end");
    }

    public List<ProductUnit> getUNITS() {
        return UNITS;
    }

    public List<ProductPeriod> getPERIODS() {
        return PERIODS;
    }

    public Map<Long, ProductUnit> getUNITS_MAP() {
        return UNITS_MAP;
    }

    public Map<Long, ProductPeriod> getPERIODS_MAP() {
        return PERIODS_MAP;
    }

    public ProductUnit getUnitById(Long id) {
        return UNITS_MAP.get(id);
    }

    public ProductPeriod getPeriodById(Long id) {
        return PERIODS_MAP.get(id);
    }

    public boolean isDefaultPeriod(Long id) {
        return id == Constant.PERIOD_ALL_YEAR_ROUND;
    }
    
    public Long getDefaultUnit() {
        return 8L;
    }
}
