package com.crop.seagulls.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.entities.ProductUnit;

public class DictProductUnitServiceImplTest extends SuperTest{

    @Autowired
    private DictProductUnitService dictProductUnitService;
    
    @Test
    public void testFindProductUnits() {
        List<ProductUnit> result = dictProductUnitService.findProductUnits();
        Assert.assertTrue("Can't find the product unit.", CollectionUtils.isNotEmpty(result));
    }

}
