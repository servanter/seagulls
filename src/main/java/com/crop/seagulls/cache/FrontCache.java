package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Banner;
import com.crop.seagulls.entities.Category;
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
        List<Long> ids = hotCategoryService.findAll();
        if (CollectionUtils.isNotEmpty(ids)) {
            List<Category> list = new ArrayList<Category>();
            for (Long id : ids) {
                list.add(categoryCache.getById(id));
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
