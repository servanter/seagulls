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
import com.crop.seagulls.entities.Buy;
import com.crop.seagulls.entities.BuyPic;
import com.crop.seagulls.entities.SellPic;
import com.crop.seagulls.service.BuyPicService;
import com.crop.seagulls.service.BuyService;
import com.crop.seagulls.util.SessionUtils;
import com.crop.seagulls.util.UploadUtils;

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
    private BuyPicService buyPicService;

    @RequestMapping(value = "/buy/publish", method = RequestMethod.GET)
    public String enterPublish(HttpSession session, Model model) {
        model.mergeAttributes(buyService.addPre(SessionUtils.getCurUser(session).getId()));
        return "buy/publish";
    }

    @ResponseBody
    @RequestMapping(value = "/buy/publish", method = RequestMethod.POST)
    public Response publish(Buy buy, HttpServletRequest request, HttpSession session) {
        Response uploadResponse = UploadUtils.upload("images/publish/", "images/publish/", Constant.BUY, request);
        if (ReturnCode.isSuccess(uploadResponse.getReturnCode())) {
            buy.setCreateUserId(SessionUtils.getCurUser(session).getId());
            Response response = buyService.add(buy, (List<String>) uploadResponse.getResult());
            return response;
        } else {
            return uploadResponse;
        }
    }
    
    @RequestMapping(value = "/buy/publishSuccess", method = RequestMethod.GET)
    public String publishSuccess(@RequestParam("id") Long id,Model model) {
        model.addAttribute("sellBuy", SellBuy.BUY.getCode());
        model.addAttribute("id", id);
        return "publish/publish_success";
    }

    @RequestMapping(value = { "/buy/buy_index" }, method = RequestMethod.GET)
    public String buyIndex(Model model) {
        Map<String, Object> map = buyService.findList(new Buy());
        model.mergeAttributes(map);
        return "buy/buy_index";
    }

    @RequestMapping(value = { "/buy/buy_list_c{cate:\\d+}" }, method = RequestMethod.GET)
    public String buyList(@PathVariable("cate")
    Long category, Model model) {
        Buy buy = new Buy();
        buy.setSearchCategoryId(category);
        Map<String, Object> map = buyService.findList(buy);
        model.mergeAttributes(map);
        model.addAttribute("s", buy);
        return "buy/buy_list";
    }
    
    @ResponseBody
    @RequestMapping(value = { "/buy/ajaxFindList/" }, method = RequestMethod.GET)
    public Response ajaxFindList(@RequestParam(value = "searchCategoryId", required = false)
    Long category, @RequestParam(value = "page", required = false)
    Integer page) {
        Buy buy = new Buy();
        buy.setSearchCategoryId(category);
        buy.setPage(page);
        return buyService.ajaxFindList(buy);
    }

    @RequestMapping("/buy/buy_detail_{id:\\d+}.html")
    public String detail(@PathVariable("id")
    Long id, HttpSession session,Model model) {
        Buy buy = new Buy();
        buy.setId(id);
        buy.setLoginUser(SessionUtils.getCurUser(session));
        model.mergeAttributes(buyService.findById(buy));
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
    Long id, HttpSession session,Model model) {
        Buy buy = new Buy();
        buy.setCreateUserId(SessionUtils.getCurUser(session).getId());
        buy.setId(id);
        Map<String, Object> map = buyService.findById(buy);
        for (Entry<String, Object> entry : map.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return "buy/my_buy_detail";
    }
    
    @RequestMapping(value = "/buy/buyPics", method = RequestMethod.GET)
    public String picDetail(@RequestParam("picId") Long picId, Model model) {
        List<BuyPic> pics = buyPicService.findPicsById(picId);
        model.addAttribute("pics", pics);
        return "buy/buy_pic";
    }
}
