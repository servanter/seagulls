package com.crop.seagulls.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.entities.ProductPeriod;

public class DictProductPeriodServiceImplTest extends SuperTest {

    @Autowired
    private DictProductPeriodService dictProductPeriodService;

    @Test
    public void testFindPeriods() {
        List<ProductPeriod> result = dictProductPeriodService.findPeriods();
        Assert.assertTrue("Can't find the product period.", CollectionUtils.isNotEmpty(result));
    }
}
