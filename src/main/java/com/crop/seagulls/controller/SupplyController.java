package com.crop.seagulls.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Supply;
import com.crop.seagulls.service.SupplyService;
import com.crop.seagulls.util.SessionUtils;
import com.crop.seagulls.util.UploadUtils;

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
    @RequestMapping(value = "/supply/publish")
    public Response publish(Supply supply, HttpServletRequest request, HttpSession session) {
        Response uploadResponse = UploadUtils.upload(request);
        if (ReturnCode.isSuccess(uploadResponse.getReturnCode())) {
            supply.setCreateUserId(SessionUtils.getCurUser(session).getId());
            Response response = supplyService.add(supply, (List<String>) uploadResponse.getResult());
            return response;
        } else {
            return uploadResponse;
        }
    }

    @RequestMapping(value = "/supply_cate_{cate:\\d+}", method = RequestMethod.GET)
    public String supplyCategory(@PathVariable("cate")
    Long category, Model model) {
        List<Category> categories = categoryCache.getByPid(category);
        if (CollectionUtils.isEmpty(categories) && categoryCache.getById(category) != null) {
            return "redirect:/supply/supply_list_c" + category + "p0t0o0n1/";
        }
        model.addAttribute("categories", categories);
        return "supply/category_list";
    }

    @RequestMapping(value = { "/supply/supply_list_c{cate:\\d+}p{province:\\d+}t{startPeriod:\\d+}o{orderBy:\\d+}n{page:\\d+}" }, method = RequestMethod.GET)
    public String supplyList(@PathVariable("cate")
    Long category, @PathVariable("province")
    Long province, @PathVariable("startPeriod")
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

    @RequestMapping("/supply/supply_order_{id:\\d+}")
    public String detail(@PathVariable("id")
    Long id, Model model) {
        Map<String, Object> map = supplyService.findById(id);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "supply/supply_detail";
    }
}
