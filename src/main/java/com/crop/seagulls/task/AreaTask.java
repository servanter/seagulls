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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        StringBuilder provinceBuilder = new StringBuilder();
//        StringBuilder locationBuilder = new StringBuilder();
//        for (Area area : areas) {
//            if (area.getParentId() == 0) {
//                provinceBuilder.append("provinces.push({id:" + area.getId() + ", pid:" + area.getParentId() + ", zh_name:'" + area.getZhName() + "', en_name:'" + area.getEnName()
//                        + "', first_letter:'" + area.getFirstLetter() + "'});\r\n");
//            } else {
//                locationBuilder.append("locations.push({id:" + area.getId() + ", pid:" + area.getParentId() + ", zh_name:'" + area.getZhName() + "', en_name:'" + area.getEnName()
//                        + "', first_letter:'" + area.getFirstLetter() + "'});\r\n");
//            }
//        }
//
//        sb.append("var elements = new Array();\r\n");
//        sb.append("elements.push('provinceId','cityId','areaId');\r\n");
//
//        sb.append("var defaultArea = {id:-1,pid:-1,zh_name:'不限',en_name:'buxian',first_letter:'B'};\r\n");
//        sb.append("var provinces = new Array();\r\n");
//        sb.append("provinces.push(defaultArea);");
//        sb.append(provinceBuilder);
//        sb.append("var locations = new Array();\r\n");
//        sb.append(locationBuilder);
//        sb.append("var AreaUtils = new AreaUtils();\r\n");
//        sb.append("function AreaUtils(){\r\n");
//        sb.append("}\r\n");
//        sb.append("AreaUtils.loadProvinces = function() {\r\n");
//        sb.append(" return provinces;\r\n");
//        sb.append("}\r\n");
//
//        sb.append("$(function(){\r\n");
//        sb.append(" init($('#sel_province'));\r\n");
//        sb.append(" $(document).on('change', '.sel_area', function(){\r\n");
//        sb.append("     var nextNode = $(this).clone();\r\n");
//        sb.append("     var alias = $(this).attr('name');\r\n");
//        sb.append("     $(nextNode).removeAttr('id');\r\n");
//        sb.append("     $(nextNode).empty();\r\n");
//        sb.append("     \r\n");
//        sb.append("     // clear\r\n");
//        sb.append("     var hasNext = getNextName(alias);\r\n");
//        sb.append("     for( ; hasNext != undefined; ) {\r\n");
//        sb.append("         var node = '[name='+hasNext+']';\r\n");
//        sb.append("         $(node).remove();\r\n");
//        sb.append("         hasNext = getNextName(hasNext);\r\n");
//        sb.append("         if(!hasNext) {\r\n");
//        sb.append("             break;\r\n");
//        sb.append("         }\r\n");
//        sb.append("     }\r\n");
//        sb.append("     \r\n");
//        sb.append("     \r\n");
//        sb.append("     var nextLocations = loadNextLoaction($(this).val());\r\n");
//        sb.append("     if(nextLocations && nextLocations.length > 0) {\r\n");
//        sb.append("         for (var i in nextLocations) {\r\n");
//        sb.append("             $(nextNode).append('<option value=\"'+nextLocations[i].id+'\">'+nextLocations[i].zh_name+'</option>')\r\n");
//        sb.append("         }\r\n");
//        sb.append("         var nextName = getNextName(alias);\r\n");
//        sb.append("         $(nextNode).attr('name', nextName);\r\n");
//        sb.append("         $(this).after(nextNode);\r\n");
//        sb.append("     }\r\n");
//        sb.append("     \r\n");
//        sb.append("     \r\n");
//        sb.append("     \r\n");
//        sb.append(" });\r\n");
//        sb.append("});\r\n");
//
//        sb.append("function init(obj) {\r\n");
//        sb.append(" $(obj).empty();\r\n");
//        sb.append(" for (var i in provinces) {\r\n");
//        sb.append("     $(obj).append('<option value=\"'+provinces[i].id+'\">'+provinces[i].zh_name+'</option>');\r\n");
//        sb.append(" }\r\n");
//        sb.append("}\r\n");
//
//        sb.append("function loadNextLoaction(pid) {\r\n");
//        sb.append(" var result = new Array();\r\n");
//        sb.append(" for(var i in locations) {\r\n");
//        sb.append("     if(locations[i].pid == pid) {\r\n");
//        sb.append("         result.push(locations[i]);\r\n");
//        sb.append("     }\r\n");
//        sb.append(" }\r\n");
//        sb.append(" if(result.length > 0) {\r\n");
//        sb.append("     result.splice(0, 0, defaultArea);\r\n");
//        sb.append(" }\r\n");
//        sb.append(" return result;\r\n");
//        sb.append("}\r\n");
//
//        sb.append("function getNextName(curName) {\r\n");
//        sb.append(" for(var i in elements) {\r\n");
//        sb.append("     if(elements[i] == curName && (parseInt(i) + 1 <= elements.length)) {\r\n");
//        sb.append("         return elements[parseInt(i) + 1]; \r\n");
//        sb.append("     }\r\n");
//        sb.append(" }\r\n");
//        sb.append(" return undefined;\r\n");
//        sb.append("}\r\n");

        try {
            FileUtils.writeStringToFile(new File(templateService.getMessage("area.js.filepath")), sb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
