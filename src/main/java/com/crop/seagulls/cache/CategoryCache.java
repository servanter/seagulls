package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Category;
import com.crop.seagulls.service.DictCategoryService;

@Component
@DependsOn("frontCache")
public class CategoryCache {

    /**
     * id & map
     */
    private Map<Long, Category> ALL_CATEGORY_MAP = new HashMap<Long, Category>();

    /**
     * pid & sub categories
     */
    private Map<Long, List<Category>> CATEGORY_RELATION_MAP = new HashMap<Long, List<Category>>();

    private List<String> CATEGORIES_DB_METHODS = new ArrayList<String>();

    /**
     * all categories
     */
    private List<Category> ALL_CATEGORY_WITH_VARIERTIES = new ArrayList<Category>();
    
    @Autowired
    private DictCategoryService dictCategoryService;

    @Autowired
    private VarietiesCache varietiesCache;
    
    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        CATEGORIES_DB_METHODS.add("setCategoryId1");
        CATEGORIES_DB_METHODS.add("setCategoryId2");
        CATEGORIES_DB_METHODS.add("setCategoryId3");
        
        List<Category> categories = dictCategoryService.findAll();
        if (CollectionUtils.isNotEmpty(categories)) {
            Map<Long, Category> map = new HashMap<Long, Category>();
            Map<Long, List<Category>> mapRelation = new HashMap<Long, List<Category>>();
            for (Category category : categories) {
                map.put(category.getId(), category);
                if(category.getId() == 1) {
                    category.setStyle("color:#00c9a1;");
                } else if (category.getId() == 2) {
                    category.setStyle("color:#4bc72f;");
                } else if (category.getId() == 3) {
                    category.setStyle("color:#df8620;");
                } else if (category.getId() == 4) {
                    category.setStyle("color:#1f9be9;");
                } else if (category.getId() == 900) {
                    category.setStyle("color:#ff8b02;");
                }
                
                if (mapRelation.containsKey(category.getPId())) {
                    mapRelation.get(category.getPId()).add(category);
                } else {
                    List<Category> curCategories = new ArrayList<Category>();
                    curCategories.add(category);
                    mapRelation.put(category.getPId(), curCategories);
                }
                category.setVarieties(varietiesCache.getByCategoryId(category.getId()));
            }
            
            ALL_CATEGORY_MAP = map;
            CATEGORY_RELATION_MAP = mapRelation;
            ALL_CATEGORY_WITH_VARIERTIES = categories;
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
        if(ObjectUtils.notEqual(id, null)) {
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
        }
        return result;
    }

    public List<Category> getCategoryVarierties() {
        return ALL_CATEGORY_WITH_VARIERTIES;
    }
    
    public void refreshAll() {
        init();
    }
}
