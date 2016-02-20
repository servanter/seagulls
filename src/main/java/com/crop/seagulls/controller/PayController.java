package com.crop.seagulls.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.entities.ConsumeOrder;
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
        sellProduct.setIp(WebUtils.getIp(request));
        User user = SessionUtils.getCurUser(request.getSession());
        if (ObjectUtils.notEqual(user.getId(), null)) {
            sellProduct.setUserId(user.getId());
            return orderService.submitSellProduct(sellProduct);
        } else {
            return new Response(ReturnCode.ERROR);
        }
    }

    @RequestMapping("/payConsumeOrder")
    public String payConsumeOrder(@RequestParam("orderId")
    String orderId, HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        if (ObjectUtils.notEqual(user.getId(), null)) {
            ConsumeOrder consumeOrder = new ConsumeOrder();
            consumeOrder.setId(orderId);
            consumeOrder.setUserId(user.getId());
            model.mergeAttributes(orderService.payConsumeOrderPre(consumeOrder));
        }
        return "pay/pay_sell_product";
    }

    @ResponseBody
    @RequestMapping("/payOrder")
    public Response payOrder(@RequestParam("orderId")
    String orderId, @RequestParam("payWay")
    Integer payWay, HttpServletRequest request) {
        // order.setIp(WebUtils.getIp(request));
        User user = SessionUtils.getCurUser(request.getSession());
        if (ObjectUtils.notEqual(user.getId(), null)) {
            ConsumeOrder order = new ConsumeOrder();
            order.setId(orderId);
            order.setUserId(user.getId());
            order.setPayType(payWay);
            return orderService.payOrder(order);
        } else {
            return new Response(ReturnCode.ERROR);
        }
    }

    @RequestMapping("/consumeOrder")
    public String consumeOrder(@RequestParam("orderId")
    String orderId, HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        if (ObjectUtils.notEqual(user.getId(), null)) {
            ConsumeOrder consumeOrder = new ConsumeOrder();
            consumeOrder.setId(orderId);
            consumeOrder.setUserId(user.getId());
            model.mergeAttributes(orderService.findConsumeOrder(consumeOrder));
        }
        return "pay/consume_order_sellproduct_detail";
    }

    @ResponseBody
    @RequestMapping("/confirmConsumeOrder")
    public Response confirmConsumeOrder(@RequestParam("orderId")
    String orderId, HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        if (ObjectUtils.notEqual(user.getId(), null)) {
            ConsumeOrder consumeOrder = new ConsumeOrder();
            consumeOrder.setId(orderId);
            consumeOrder.setUserId(user.getId());
            return orderService.confirmConsumeOrder(consumeOrder);

        }
        return new Response(ReturnCode.ERROR);
    }
    
    
    
    @RequestMapping("/paySuccess")
    public String paySucces(@RequestParam("orderId")
    String orderId, HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        if (ObjectUtils.notEqual(user.getId(), null)) {
            ConsumeOrder consumeOrder = new ConsumeOrder();
            consumeOrder.setId(orderId);
            consumeOrder.setUserId(user.getId());
            model.mergeAttributes(orderService.paySuccess(consumeOrder));
        }
        return "pay/pay_success";
    }
    
    @RequestMapping("/payFail")
    public String payFail(@RequestParam("orderId")
    String orderId, HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        if (ObjectUtils.notEqual(user.getId(), null)) {
            ConsumeOrder consumeOrder = new ConsumeOrder();
            consumeOrder.setId(orderId);
            consumeOrder.setUserId(user.getId());
            model.mergeAttributes(orderService.payFail(consumeOrder));
        }
        return "pay/pay_fail";
    }

}
