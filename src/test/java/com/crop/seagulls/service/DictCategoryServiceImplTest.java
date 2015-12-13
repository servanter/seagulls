package com.crop.seagulls.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.entities.Category;

public class DictCategoryServiceImplTest extends SuperTest {

    @Autowired
    private DictCategoryService dictCategoryService;

    @Test
    public void testFindList() {
        Assert.assertTrue(!dictCategoryService.findAll().isEmpty());
    }

    @Test
    public void testFindByPId() {
        List<Category> categories = dictCategoryService.findByPId(1L);
        System.out.println(categories);
        Assert.assertTrue("Cant't find the categories.", CollectionUtils.isNotEmpty(categories));
    }

    @Test
    public void testSaveAndModify() {
//        Category category = new Category(11111L, 12321L, "apple", "苹果啊", "A");
//        boolean b1 = dictCategoryService.save(category);
//        System.out.println("---------" + b1);
//        category.setZhName("啊啊啊 ");
//        category.setEnName("asdsadasd");
//        category.setFirstLetter("B");
//        boolean b2 = dictCategoryService.modify(category);
//        Assert.assertTrue("Can't save and modify category.", b1 && b2);
    }

}
