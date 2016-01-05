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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.bean.SellBuy;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.Sell;
import com.crop.seagulls.entities.SellPic;
import com.crop.seagulls.service.SellPicService;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.util.SessionUtils;
import com.crop.seagulls.util.UploadUtils;

/**
 * Sell controller
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class SellController {

    @Autowired
    private SellService sellService;
    
    @Autowired
    private SellPicService sellPicService;

    @RequestMapping(value = "/sell/publish", method = RequestMethod.GET)
    public String enterPublish(HttpSession session, Model model) {
        model.mergeAttributes(sellService.addPre(SessionUtils.getCurUser(session).getId()));
        return "sell/publish";
    }

    @ResponseBody
    @RequestMapping(value = "/sell/publish", method = RequestMethod.POST)
    public Response publish(Sell sell, HttpServletRequest request, HttpSession session) {
        Response uploadResponse = UploadUtils.upload("images/publish/", "images/publish/", Constant.SELL, request);
        if (ReturnCode.isSuccess(uploadResponse.getReturnCode())) {
            sell.setCreateUserId(SessionUtils.getCurUser(session).getId());
            Response response = sellService.add(sell, (List<String>) uploadResponse.getResult());
            return response;
        } else {
            return uploadResponse;
        }
    }
    
    @RequestMapping(value = "/sell/publishSuccess", method = RequestMethod.GET)
    public String publishSuccess(@RequestParam("id") Long id,Model model) {
        model.addAttribute("sellBuy", SellBuy.SELL.getCode());
        model.addAttribute("id", id);
        return "publish/publish_success";
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

    @ResponseBody
    @RequestMapping(value = { "/sell/ajaxFindList/" }, method = RequestMethod.GET)
    public Response ajaxFindList(@RequestParam(value = "searchCategoryId", required = false)
    Long category, @RequestParam(value = "page", required = false)
    Integer page) {
        Sell sell = new Sell();
        sell.setSearchCategoryId(category);
        sell.setPage(page);
        return sellService.ajaxFindList(sell);
    }

    @RequestMapping("/sell/sell_detail_{id:\\d+}.html")
    public String detail(@PathVariable("id")
    Long id, HttpSession session, Model model) {
        Sell sell = new Sell();
        sell.setId(id);
        sell.setLoginUser(SessionUtils.getCurUser(session));
        model.mergeAttributes(sellService.findById(sell));
        return "sell/sell_detail";
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
    Long id, HttpSession session, Model model) {
        Sell sell = new Sell();
        sell.setId(id);
        sell.setLoginUser(SessionUtils.getCurUser(session));
        Map<String, Object> map = sellService.findById(sell);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "supply/my_supply_detail";
    }
    
    @RequestMapping(value = "/sell/sellPics", method = RequestMethod.GET)
    public String picDetail(@RequestParam("picId") Long picId, Model model) {
        List<SellPic> pics = sellPicService.findPicsById(picId);
        model.addAttribute("pics", pics);
        return "sell/sell_pic";
    }
}
