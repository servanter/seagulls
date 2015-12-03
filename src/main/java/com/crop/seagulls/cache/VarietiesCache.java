package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Varieties;
import com.crop.seagulls.service.VarietiesService;

@Component
public class VarietiesCache {

    private static Map<Long, List<Varieties>> ALL_VARIETIES_MAP = new HashMap<Long, List<Varieties>>();

    @Autowired
    private VarietiesService varietiesService;

    @PostConstruct
    public void init() {
        List<Varieties> varieties = varietiesService.findAll();
        Map<Long, List<Varieties>> map = new HashMap<Long, List<Varieties>>();
        for (Varieties v : varieties) {
            if (map.containsKey(v.getCategoryId())) {
                map.get(v.getCategoryId()).add(v);
            } else {
                List<Varieties> list = new ArrayList<Varieties>();
                list.add(v);
                map.put(v.getCategoryId(), list);
            }
        }
        ALL_VARIETIES_MAP = map;
    }

    public Map<Long, List<Varieties>> getAllVarieties() {
        return ALL_VARIETIES_MAP;
    }

    public List<Varieties> getByCategoryId(Long cateogryId) {
        return ALL_VARIETIES_MAP.get(cateogryId);
    }
}
