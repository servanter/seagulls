package com.crop.seagulls.task;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.VarietiesCache;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Varieties;
import com.crop.seagulls.service.DictCategoryService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.Logger;

@Component
public class CategoryTask {

    private Logger logger = new Logger(CategoryTask.class);

    @Autowired
    private DictCategoryService dictCategoryService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private CategoryCache categoryCache;
    
    @Autowired
    private VarietiesCache varietiesCache;

//     @Scheduled(cron = "0/3 * * * * ?")
    public void start() {
        logger.info("start ======================");
        
        
        // dsy.add("0",["水果","蔬菜","肉禽蛋","水产"]);
        List<Category> parents = categoryCache.getByPid(0L);
        StringBuilder p = new StringBuilder();
        p.append("dsy.add(\"0\",[");
        for (Category parent : parents) {
            p.append("{\"id\":" + parent.getId() + ", \"name\":\"" + parent.getZhName() + "\"},");
        }
        p = new StringBuilder(p.substring(0, p.length() - 1));
        p.append("]);\r\n");


        // dsy.add("0_0",["香蕉","龙眼","荔枝","芒果","菠萝","菠萝蜜","火龙果","莲雾","牛油果","山竹","木瓜","椰子","榴莲","橙子","柠檬","桔子","柚子","西瓜","石榴","杨桃","苹果","梨","葡萄/提子","樱桃/车厘子","哈密瓜"]);
        int index = 0;
        StringBuilder s = new StringBuilder();
        StringBuilder vvv = new StringBuilder();
        for (Category parent : parents) {
            List<Category> categories = categoryCache.getByPid(parent.getId());
            if(CollectionUtils.isNotEmpty(categories)) {
                
                StringBuilder every = new StringBuilder();
                int b = 0;
                for (Category sub : categories) {
                    every.append("{\"id\":" + sub.getId() + ", \"name\":\"" + sub.getZhName() + "\"},");
                    
                    if(varietiesCache.getAllVarieties().containsKey(sub.getId())) {
                        List<Varieties> varieties  = varietiesCache.getAllVarieties().get(sub.getId());
                        StringBuilder mm = new StringBuilder();
                        if(CollectionUtils.isNotEmpty(varieties))  {
                            StringBuilder builder  =new StringBuilder();
                            builder.append("{\"id\":-1, \"name\":\"全部\"},");
                            for(Varieties v:varieties) {
                                builder.append("{\"id\":" + v.getId() + ", \"name\":\"" + v.getZhName() + "\"},");
                            }
                            mm.append("dsy.add(\"0_" + index + "_" + b + "\",[" + builder.substring(0, builder.length() - 1) + "]);\r\n");
                        }
                        vvv.append(mm);
                    }
                    b++;
                }
                s.append("dsy.add(\"0_" + index + "\",[" + every.substring(0, every.length() - 1) + "]);\r\n");
                index++;
            }
        }
        
        StringBuilder pre = new StringBuilder();
        pre.append("function Dsy(){\r\n");
        pre.append("    this.Items = {};\r\n");
        pre.append("};\r\n");
        pre.append("Dsy.prototype.add = function(id,iArray){\r\n");
        pre.append("    this.Items[id] = iArray;\r\n");
        pre.append("};\r\n");
        pre.append("Dsy.prototype.Exists = function(id){\r\n");
        pre.append("    if(typeof(this.Items[id]) == \"undefined\") return false;\r\n");
        pre.append("    return true;\r\n");
        pre.append("};\r\n");
        pre.append("var dsy = new Dsy();\r\n");
        String x = pre.toString() + p.toString() + s.toString() +  vvv.toString();
        
        try {
            FileUtils.writeStringToFile(new File(templateService.getMessage("category.js.filepath")), x.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("end ======================");
    }
}
