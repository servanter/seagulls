package com.crop.seagulls.task;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.entities.Area;
import com.crop.seagulls.service.DictAreaService;
import com.crop.seagulls.util.Logger;

@Service("areaTask")
public class AreaTask {

    private Logger logger = new Logger(AreaTask.class);
    @Autowired
    private DictAreaService dictAreaService;

    public void start() {
        logger.info("start ======================");
        System.out.println("11111111111111111111111111111");
        List<Area> areas = dictAreaService.findList();

        StringBuilder sb = new StringBuilder();
        StringBuilder provinceBuilder = new StringBuilder();
        StringBuilder locationBuilder = new StringBuilder();
        for (Area area : areas) {
            if (area.getPId() == 0) {
                provinceBuilder.append("provinces.push({id:" + area.getId() + ", pid:" + area.getPId() + ", zh_name:'" + area.getZhName() + "', en_name:'" + area.getEnName()
                        + "', first_letter:'" + area.getFirstLetter() + "'});\r\n");
            } else {
                locationBuilder.append("locations.push({id:" + area.getId() + ", pid:" + area.getPId() + ", zh_name:'" + area.getZhName() + "', en_name:'" + area.getEnName()
                        + "', first_letter:'" + area.getFirstLetter() + "'});\r\n");
            }
        }
        
        
        
        
        
        
        
        
        sb.append("var elements = new Array();\r\n");
        sb.append("elements.push('province','city','location');\r\n");

        sb.append("var defaultArea = {id:-1,pid:-1,zh_name:'不限',en_name:'buxian',first_letter:'B'};\r\n");
        sb.append("var provinces = new Array();\r\n");
        sb.append(provinceBuilder);
        sb.append("var locations = new Array();\r\n");
        sb.append(locationBuilder);
        sb.append("var AreaUtils = new AreaUtils();\r\n");
        sb.append("function AreaUtils(){\r\n");
        sb.append("}\r\n");
        sb.append("AreaUtils.loadProvinces = function() {\r\n");
        sb.append(" return provinces;\r\n");
        sb.append("}\r\n");

        sb.append("$(function(){\r\n");
        sb.append(" init($('#sel_province'));\r\n");
        sb.append(" $(document).on('change', '.sel_area', function(){\r\n");
        sb.append("     var nextNode = $(this).clone();\r\n");
        sb.append("     var alias = $(this).attr('name');\r\n");
        sb.append("     $(nextNode).removeAttr('id');\r\n");
        sb.append("     $(nextNode).empty();\r\n");
        sb.append("     \r\n");
        sb.append("     // clear\r\n");
        sb.append("     var hasNext = getNextName(alias);\r\n");
        sb.append("     for( ; hasNext != undefined; ) {\r\n");
        sb.append("         var node = '[name='+hasNext+']';\r\n");
        sb.append("         $(node).remove();\r\n");
        sb.append("         hasNext = getNextName(hasNext);\r\n");
        sb.append("         if(!hasNext) {\r\n");
        sb.append("             break;\r\n");
        sb.append("         }\r\n");
        sb.append("     }\r\n");
        sb.append("     \r\n");
        sb.append("     \r\n");
        sb.append("     var nextLocations = loadNextLoaction($(this).val());\r\n");
        sb.append("     if(nextLocations && nextLocations.length > 0) {\r\n");
        sb.append("         for (var i in nextLocations) {\r\n");
        sb.append("             $(nextNode).append('<option value=\"'+nextLocations[i].id+'\">'+nextLocations[i].zh_name+'</option>')\r\n");
        sb.append("         }\r\n");
        sb.append("         var nextName = getNextName(alias);\r\n");
        sb.append("         $(nextNode).attr('name', nextName);\r\n");
        sb.append("         $(this).after(nextNode);\r\n");
        sb.append("     }\r\n");
        sb.append("     \r\n");
        sb.append("     \r\n");
        sb.append("     \r\n");
        sb.append(" });\r\n");
        sb.append("});\r\n");

        sb.append("function init(obj) {\r\n");
        sb.append(" $(obj).empty();\r\n");
        sb.append(" for (var i in provinces) {\r\n");
        sb.append("     $(obj).append('<option value=\"'+provinces[i].id+'\">'+provinces[i].zh_name+'</option>');\r\n");
        sb.append(" }\r\n");
        sb.append("}\r\n");

        sb.append("function loadNextLoaction(pid) {\r\n");
        sb.append(" var result = new Array();\r\n");
        sb.append(" for(var i in locations) {\r\n");
        sb.append("     if(locations[i].pid == pid) {\r\n");
        sb.append("         result.push(locations[i]);\r\n");
        sb.append("     }\r\n");
        sb.append(" }\r\n");
        sb.append(" if(result.length > 0) {\r\n");
        sb.append("     result.splice(0, 0, defaultArea);\r\n");
        sb.append(" }\r\n");
        sb.append(" return result;\r\n");
        sb.append("}\r\n");

        sb.append("function getNextName(curName) {\r\n");
        sb.append(" for(var i in elements) {\r\n");
        sb.append("     if(elements[i] == curName && (parseInt(i) + 1 <= elements.length)) {\r\n");
        sb.append("         return elements[parseInt(i) + 1]; \r\n");
        sb.append("     }\r\n");
        sb.append(" }\r\n");
        sb.append(" return undefined;\r\n");
        sb.append("}\r\n");
        
        try {
            FileUtils.writeStringToFile(new File("D:\\aa.js"), sb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
