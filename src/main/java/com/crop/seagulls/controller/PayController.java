package com.crop.seagulls.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.PayTypeEnum;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.entities.Order;
import com.crop.seagulls.entities.SellProduct;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.OrderService;
import com.crop.seagulls.util.SessionUtils;
import com.crop.seagulls.util.WebUtils;

@Controller
@RequestMapping("/pay")
public class PayController {
    
    @Autowired
    private OrderService orderService;
    
    @ResponseBody
    @RequestMapping("/submitSellProduct")
    public Response submitOrder(SellProduct sellProduct, HttpServletRequest request) {
        User user = SessionUtils.getCurUser(request.getSession());
        if (ObjectUtils.notEqual(user.getId(), null)) {
            sellProduct.setUserId(user.getId());
            return orderService.submitSellProduct(sellProduct);
        } else {
            return new Response(ReturnCode.ERROR);
        }
    }
    
    @ResponseBody
    @RequestMapping("/generateOrderId")
    public Response generateOrderId(Order order, HttpServletRequest request) {
        order.setIp(WebUtils.getIp(request));
        User user = SessionUtils.getCurUser(request.getSession());
        if (ObjectUtils.notEqual(user.getId(), null)) {
            order.setUserId(user.getId());
            order.setPayType(PayTypeEnum.WEIXIN.getCode());
            return orderService.generateOrder(order);
        } else {
            return new Response(ReturnCode.ERROR);
        }
    }
    
}
