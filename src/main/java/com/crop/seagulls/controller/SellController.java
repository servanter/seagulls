package com.crop.seagulls.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.entities.Sell;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.util.SessionUtils;
import com.crop.seagulls.util.UploadUtils;

/**
 * Supply controller
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class SellController {

    @Autowired
    private SellService sellService;

    @Autowired
    private ProductRelationCache productRelationCache;

    @RequestMapping(value = "/sell/publish", method = RequestMethod.GET)
    public String enterPublish(Model model) {
        model.addAttribute("units", productRelationCache.getUNITS());
        model.addAttribute("periods", productRelationCache.getPERIODS());
        return "supply/publish";
    }

    @ResponseBody
    @RequestMapping(value = "/sell/publish", method = RequestMethod.POST)
    public Response publish(Sell supply, HttpServletRequest request, HttpSession session) {
        Response uploadResponse = UploadUtils.upload("images/publish/","images/publish/",request);
        if (ReturnCode.isSuccess(uploadResponse.getReturnCode())) {
            supply.setCreateUserId(SessionUtils.getCurUser(session).getId());
            Response response = sellService.add(supply, (List<String>) uploadResponse.getResult());
            return response;
        } else {
            return uploadResponse;
        }
    }

    @RequestMapping(value = { "/sell/sell_index" }, method = RequestMethod.GET)
    public String sellIndex(Model model) {
        Map<String, Object> map = sellService.findList(new Sell());
        model.mergeAttributes(map);
        return "sell/sell_index";
    }

    @RequestMapping(value = { "/sell/sell_list_c{cate:\\d+}" }, method = RequestMethod.GET)
    public String sellList(@PathVariable("cate")
    Long category, Model model) {
        Sell sell = new Sell();
        sell.setSearchCategoryId(category);
        Map<String, Object> map = sellService.findList(sell);
        model.mergeAttributes(map);
        model.addAttribute("s", sell);
        return "sell/sell_list";
    }

    @RequestMapping("/supply/sell_detail_{id:\\d+}")
    public String detail(@PathVariable("id")
    Long id, Model model) {
        Map<String, Object> map = sellService.findById(id);
        model.mergeAttributes(map);
        return "supply/supply_detail";
    }

    @RequestMapping(value = "/sell/my_sell_list_{page:\\d+}")
    public String mySupplyList(@PathVariable("page")
    Integer page, HttpSession session, Model model) {
        Sell supply = new Sell();
        supply.setPage(page);
        supply.setCreateUserId(SessionUtils.getCurUser(session).getId());
        Map<String, Object> map = sellService.findByUserId(supply);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "supply/my_supply_list";
    }

    @RequestMapping("/sell/my_sell_order_{id:\\d+}")
    public String myDetail(@PathVariable("id")
    Long id, Model model) {
        Map<String, Object> map = sellService.findById(id);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "supply/my_supply_detail";
    }
}
