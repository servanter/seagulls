package com.crop.seagulls.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Supply;
import com.crop.seagulls.service.SupplyService;
import com.crop.seagulls.util.SessionUtils;

/**
 * Supply controller
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private ProductRelationCache productRelationCache;

    @Autowired
    private CategoryCache categoryCache;

    @RequestMapping(value = "/supply/publish", method = RequestMethod.GET)
    public String enterPublish(Model model) {
        model.addAttribute("units", productRelationCache.getUNITS());
        model.addAttribute("periods", productRelationCache.getPERIODS());
        return "supply/publish";
    }

    @ResponseBody
    @RequestMapping(value = "/supply/publish", method = RequestMethod.POST)
    public Response publish(Supply supply, HttpSession session) {
        supply.setCreateUserId(SessionUtils.getCurUser(session).getId());
        Response response = supplyService.add(supply);
        return response;
    }

    @RequestMapping(value = "/supply_cate_{cate:\\d+}", method = RequestMethod.GET)
    public String supplyCategory(@PathVariable("cate")
    Long category, Model model) {
        List<Category> categories = categoryCache.getByPid(category);
        if (CollectionUtils.isEmpty(categories) && categoryCache.getById(category) != null) {
            return "redirect:/supply/supply_list_c" + category+ "p0t0o0n1/";
        }
        model.addAttribute("categories", categories);
        return "supply/category_list";
    }

    @RequestMapping(value = { "/supply/supply_list_c{cate:\\d+}p{province:\\d+}t{startPeriod:\\d+}o{orderBy:\\d+}n{page:\\d+}" }, method = RequestMethod.GET)
    public String supplyList(@PathVariable("cate")
    Long category, @PathVariable("province")
    Long province,@PathVariable("startPeriod")
    Long startPeriod, @PathVariable("orderBy")
    Integer orderBy, @PathVariable("page")
    Integer page, Model model) {
        Supply supply = new Supply();
        supply.setSearchCategoryId(category);
        supply.setProvinceId(province);
        supply.setSearchOrderBy(orderBy);
        supply.setStartTime(startPeriod);
        supply.setPage(page);
        Map<String, Object> map = supplyService.querySuppliesWithExt(supply);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        model.addAttribute("s", supply);
        return "supply/supply_list";
    }
}
