package com.crop.seagulls.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.cache.AreaCache;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.service.DictAreaService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.Logger;

@Component
public class AreaTask {

    private Logger logger = new Logger(AreaTask.class);

    @Autowired
    private DictAreaService dictAreaService;

    @Autowired
    private TemplateService templateService;
    
    @Autowired
    private AreaCache areaCache;

//    @Scheduled(cron = "0/3 * * * * ?")
    public void start() {
        logger.info("start ======================");
        List<Area> areas = dictAreaService.findList();

        List<Area> provinces = new ArrayList<Area>();
        StringBuilder sb = new StringBuilder();
        
        StringBuilder proBuilder =  new StringBuilder();
        for(Area area:areas) {
            if(area.getParentId() == 0 ) {
                provinces.add(area);
                proBuilder.append(area.getId()+":'"+area.getZhName()+"',");
            }
        }
        sb.append("'0':{" + proBuilder.substring(0,proBuilder.length() - 1) + "},\r\n");
        
        StringBuilder citySBuilder = new StringBuilder();
        StringBuilder locationSBuilder = new StringBuilder();
        
        for(Area province: provinces) {
            List<Area> cities = areaCache.getByPId(province.getId());
            StringBuilder cityBuilder = new StringBuilder();
            if(CollectionUtils.isNotEmpty(cities)) {
                for(Area city:cities) {
                    List<Area> locations = areaCache.getByPId(city.getId());
                    StringBuilder locBuilder = new StringBuilder();
                    if(CollectionUtils.isNotEmpty(locations)) {
                        for(Area loc:locations) {
                            locBuilder.append(loc.getId()+":'"+loc.getZhName()+"',");
                        }
                        locationSBuilder.append("'0,"+ province.getId() +","+city.getId()+"':{" + locBuilder.substring(0,locBuilder.length() - 1) + "},\r\n");
                    } else {
                        
                    }
                    cityBuilder.append(city.getId()+":'"+city.getZhName()+"',");
                }
                if(cityBuilder.length() > 0 ) {
                    citySBuilder.append("'0,"+ province.getId() +"':{" + cityBuilder.substring(0,cityBuilder.length() - 1) + "},\r\n");
                }
            }
        }
        sb.append(citySBuilder);
        sb.append(locationSBuilder);

        try {
            FileUtils.writeStringToFile(new File(templateService.getMessage("area.js.filepath")), sb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
