package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Banner;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.HotCategory;
import com.crop.seagulls.service.BannerService;
import com.crop.seagulls.service.HotCategoryService;
import com.crop.seagulls.util.Logger;

@Component
public class FrontCache {

    public static Logger logger = Logger.getLogger(FrontCache.class);

    private List<Category> hotCategories = new ArrayList<Category>();
    private List<Banner> indexBanners = new ArrayList<Banner>();

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private HotCategoryService hotCategoryService;

    @Autowired
    private BannerService bannerService;

    @PostConstruct
    public void init() {
        logger.info("frontcache start");

        logger.info("-------------------{0}----------------", "hotCategory");
        List<HotCategory> hs = hotCategoryService.findAll();
        if (CollectionUtils.isNotEmpty(hs)) {
            List<Category> list = new ArrayList<Category>();
            for (HotCategory hotCategory : hs) {
                list.add(categoryCache.getById(hotCategory.getCategoryId()));
            }
            hotCategories = list;
        }
        logger.info("-------------------{0}----------------", "indexBanners");
        indexBanners = bannerService.findAll();

        logger.info("frontcache end");
    }

    public List<Category> getHotCategories() {
        return hotCategories;
    }

    public List<Banner> getIndexBanners() {
        return indexBanners;
    }

}
