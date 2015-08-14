package com.crop.seagulls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.cache.ProductRelationCache;
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
@RequestMapping("/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private ProductRelationCache productRelationCache;
    
    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String enterPublish(Model model) {
        model.addAttribute("units", productRelationCache.getUNITS());
        model.addAttribute("periods", productRelationCache.getPERIODS());
        return "supply/publish";
    }

    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public Response publish(Supply supply, HttpSession session) {
        supply.setCreateUserId(SessionUtils.getCurUser(session).getId());
        Response response = supplyService.add(supply);
        return response;
    }
}
