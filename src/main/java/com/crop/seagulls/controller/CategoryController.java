package com.crop.seagulls.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Supply;
import com.crop.seagulls.service.SupplyService;

@Controller
public class CategoryController {

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private SupplyService supplyService;

    @RequestMapping(value = "/supply_cate_{cate:\\d+}", method = RequestMethod.GET)
    public String supplyCategory(@PathVariable("cate")
    Long category, Model model) {
        List<Category> categories = categoryCache.getByPid(category);
        if (CollectionUtils.isEmpty(categories) && categoryCache.getById(category) != null) {
            return "redirect:/supply_list_" + category;
        }
        model.addAttribute("categories", categories);
        return "supply/category_list";
    }

    @RequestMapping(value = "/supply_list_c{cate:\\d+}p{province:\\d+}o{orderBy:\\d+}", method = RequestMethod.GET)
    public String supplyList(@PathVariable("cate")
    Long category, @PathVariable("province")
    Long province, @PathVariable("orderBy")
    Integer orderBy, Model model) {
        Supply supply = new Supply();
        supply.setSearchCategoryId(category);
        supply.setProvinceId(province);
        supply.setSearchOrderBy(orderBy);
        Map<String, Object> map = supplyService.querySuppliesWithExt(supply);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        model.addAttribute("s", supply);
        return "supply/supply_list";
    }
}
