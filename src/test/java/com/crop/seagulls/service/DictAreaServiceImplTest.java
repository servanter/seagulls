package com.crop.seagulls.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.util.PinyinUtils;

public class DictAreaServiceImplTest extends SuperTest {

    @Autowired
    private DictAreaService dictAreaService;

    @Test
    public void testFindList() {
        Assert.assertTrue("Can't find the area.", CollectionUtils.isNotEmpty(dictAreaService.findList()));
        List<Area> areas = dictAreaService.findList();
        System.out.println("====================");
        for(Area area : areas) {
            String enName = PinyinUtils.getPinYin(area.getZhName());
            area.setEnName(enName);
            area.setFirstLetter(String.valueOf(enName.charAt(0)).toUpperCase());
            dictAreaService.modify(area);
        }
    }

    @Test
    public void testModify() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testSave() {
    }

    @Test
    public void testFindByPId() {
    }

}
