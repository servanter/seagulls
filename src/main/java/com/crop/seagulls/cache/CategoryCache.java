package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Category;
import com.crop.seagulls.service.DictCategoryService;

@Component
public class CategoryCache {

    private static Map<Long, Category> ALL_CATEGORY_MAP = new HashMap<Long, Category>();

    private static Map<Long, List<Category>> CATEGORY_RELATION_MAP = new HashMap<Long, List<Category>>();

    private static List<String> CATEGORIES_DB_METHODS = new ArrayList<String>();

    @Autowired
    private DictCategoryService dictCategoryService;

    @PostConstruct
    public void initCategories2Columns() {
        CATEGORIES_DB_METHODS.add("setCategoryId1");
        CATEGORIES_DB_METHODS.add("setCategoryId2");
        CATEGORIES_DB_METHODS.add("setCategoryId3");
    }

    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        List<Category> categories = dictCategoryService.findList();
        if (CollectionUtils.isNotEmpty(categories)) {
            Map<Long, Category> map = new HashMap<Long, Category>();
            Map<Long, List<Category>> mapRelation = new HashMap<Long, List<Category>>();
            for (Category category : categories) {
                map.put(category.getId(), category);

                if (mapRelation.containsKey(category.getPId())) {
                    mapRelation.get(category.getPId()).add(category);
                } else {
                    List<Category> curCategories = new ArrayList<Category>();
                    curCategories.add(category);
                    mapRelation.put(category.getPId(), curCategories);
                }
            }
            ALL_CATEGORY_MAP = map;
            CATEGORY_RELATION_MAP = mapRelation;
        }
    }

    public Category getById(Long id) {
        if (ALL_CATEGORY_MAP.containsKey(id)) {
            return ALL_CATEGORY_MAP.get(id);
        }
        return null;
    }

    public List<Category> getByPid(Long pid) {
        return CATEGORY_RELATION_MAP.get(pid);
    }

    public Map<String, Category> getSequenceCategoies(Long id) {
        Map<String, Category> result = new HashMap<String, Category>();
        List<Long> categoies = new ArrayList<Long>();
        if (ALL_CATEGORY_MAP.containsKey(id)) {
            Category cur = ALL_CATEGORY_MAP.get(id);
            categoies.add(id);

            Category parent = null;
            for (parent = ALL_CATEGORY_MAP.get(cur.getPId()); parent != null;) {
                if (parent != null) {
                    categoies.add(parent.getId());
                    parent = ALL_CATEGORY_MAP.get(parent.getPId());
                }
            }
        }

        if (CollectionUtils.isNotEmpty(categoies)) {

            // before : cur parent parentparent after : parentparent parent cur
            List<String> methods = CATEGORIES_DB_METHODS;
            Collections.reverse(categoies);
            for (int i = 0; i < categoies.size(); i++) {
                if (methods.size() > i) {
                    String column = methods.get(i);
                    Long categoryId = categoies.get(i);
                    result.put(column, ALL_CATEGORY_MAP.get(categoryId));
                }
            }
        }
        return result;
    }
}
