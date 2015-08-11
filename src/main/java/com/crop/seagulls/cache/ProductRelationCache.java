package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    private static List<ProductPeriod> PERIODS = new ArrayList<ProductPeriod>();

    @Autowired
    private DictProductUnitService dictProductUnitService;

    @Autowired
    private DictProductPeriodService dictProductPeriodService;

    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        logger.info("ProductRelationCache start");
        UNITS = dictProductUnitService.findProductUnits();
        PERIODS = dictProductPeriodService.findPeriods();
    }

    public static List<ProductUnit> getUNITS() {
        return UNITS;
    }

    public static List<ProductPeriod> getPERIODS() {
        return PERIODS;
    }
}
