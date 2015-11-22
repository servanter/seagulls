package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Area;
import com.crop.seagulls.service.DictAreaService;

@Component
public class AreaCache {

    private static Map<Long, Area> ALL_AREA_MAP = new HashMap<Long, Area>();

    private static Map<Long, List<Area>> AREA_RELATION_MAP = new HashMap<Long, List<Area>>();

    @Autowired
    private DictAreaService dictAreaService;

    // @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        List<Area> areas = dictAreaService.findList();
        if (CollectionUtils.isNotEmpty(areas)) {
            Map<Long, Area> map = new HashMap<Long, Area>();
            Map<Long, List<Area>> mapRelation = new HashMap<Long, List<Area>>();
            for (Area Area : areas) {
                map.put(Area.getId(), Area);
                if (mapRelation.containsKey(Area.getParentId())) {
                    mapRelation.get(Area.getParentId()).add(Area);
                } else {
                    List<Area> curAreas = new ArrayList<Area>();
                    curAreas.add(Area);
                    mapRelation.put(Area.getParentId(), curAreas);
                }
            }
            ALL_AREA_MAP = map;
            AREA_RELATION_MAP = mapRelation;
        }
    }

    public Area getById(Long id) {
        if (ALL_AREA_MAP.containsKey(id)) {
            return ALL_AREA_MAP.get(id);
        }
        return null;
    }

    public List<Area> getByPId(Long pid) {
        return AREA_RELATION_MAP.get(pid);
    }

    public boolean isSpecial(Long id) {
        return id == 1 || id == 2 || id == 3 || id == 4;
    }
}
