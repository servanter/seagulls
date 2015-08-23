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
import com.crop.seagulls.entities.Buy;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.service.BuyService;
import com.crop.seagulls.util.SessionUtils;

/**
 * Buy controller
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class BuyController {

    @Autowired
    private BuyService buyService;

    @Autowired
    private ProductRelationCache productRelationCache;

    @Autowired
    private CategoryCache categoryCache;

    @RequestMapping(value = "/buy/publish", method = RequestMethod.GET)
    public String enterPublish(Model model) {
        model.addAttribute("units", productRelationCache.getUNITS());
        model.addAttribute("periods", productRelationCache.getPERIODS());
        return "buy/publish";
    }

    @ResponseBody
    @RequestMapping(value = "/buy/publish", method = RequestMethod.POST)
    public Response publish(Buy buy, HttpSession session) {
        buy.setCreateUserId(SessionUtils.getCurUser(session).getId());
        Response response = buyService.add(buy);
        return response;
    }

    @RequestMapping(value = "/buy_cate_{cate:\\d+}", method = RequestMethod.GET)
    public String supplyCategory(@PathVariable("cate")
    Long category, Model model) {
        List<Category> categories = categoryCache.getByPid(category);
        if (CollectionUtils.isEmpty(categories) && categoryCache.getById(category) != null) {
            return "redirect:/buy/buy_list_c" + category + "p0t0n1/";
        }
        model.addAttribute("categories", categories);
        return "buy/category_list";
    }

    @RequestMapping(value = { "/buy/buy_list_c{cate:\\d+}p{province:\\d+}t{startPeriod:\\d+}n{page:\\d+}" }, method = RequestMethod.GET)
    public String supplyList(@PathVariable("cate")
    Long category, @PathVariable("province")
    Long province, @PathVariable("startPeriod")
    Long startPeriod, @PathVariable("page")
    Integer page, Model model) {
        Buy buy = new Buy();
        buy.setSearchCategoryId(category);
        buy.setStartTime(startPeriod);
        buy.setProvinceId(province);
        buy.setPage(page);
        Map<String, Object> map = buyService.querySuppliesWithExt(buy);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        model.addAttribute("s", buy);
        return "buy/buy_list";
    }

    @RequestMapping("/buy/buy_order_{id:\\d+}")
    public String detail(@PathVariable("id")
    Long id, Model model) {
        Map<String, Object> map = buyService.findById(id);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "buy/buy_detail";
    }

    @RequestMapping(value = { "/buy/my_buy_list_{page:\\d+}" }, method = RequestMethod.GET)
    public String supplyList(@PathVariable("page")
    Integer page, HttpSession session, Model model) {
        Buy buy = new Buy();
        buy.setPage(page);
        buy.setCreateUserId(SessionUtils.getCurUser(session).getId());
        Map<String, Object> map = buyService.findByUserId(buy);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "buy/my_buy_list";
    }

    @RequestMapping("/buy/my_buy_order_{id:\\d+}")
    public String myDetail(@PathVariable("id")
    Long id, Model model) {
        Map<String, Object> map = buyService.findById(id);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "buy/my_buy_detail";
    }
}
