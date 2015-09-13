package com.crop.seagulls.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.service.MenuService;

@Component
public class MenuCache {

    private static Map<Long, Menu> MENU_MAP = new HashMap<Long, Menu>();

    @Autowired
    private MenuService menuService;

    @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        Map<Long, Menu> menuMap = new HashMap<Long, Menu>();

        List<Menu> menus = menuService.findAll();
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu m : menus) {
                menuMap.put(m.getId(), m);
            }
        }
        if (MapUtils.isNotEmpty(menuMap)) {
            MENU_MAP = menuMap;
        }

    }

    public Map<Long, Menu> getMenuMap() {
        return MENU_MAP;
    }

    public Menu getById(Long id) {
        return MENU_MAP.get(id);
    }
}
