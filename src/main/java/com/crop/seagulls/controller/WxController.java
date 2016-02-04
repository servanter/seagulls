package com.crop.seagulls.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.third.WeiXin;
import com.crop.seagulls.bean.wx.WxRequest;
import com.crop.seagulls.entities.Order;
import com.crop.seagulls.service.OrderService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.WXDispatchService;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.Tools;
import com.crop.seagulls.util.WebUtils;
import com.crop.seagulls.util.weixin.WXBizMsgCrypt;

/**
 * Wei xin controller
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
@RequestMapping("/external/weixin")
public class WxController {

    public static Logger logger = Logger.getLogger(WxController.class);

    @Autowired
    private TemplateService templateService;

    @Autowired
    private WXDispatchService wxDispatchService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/dispatch", method = RequestMethod.GET)
    public void validate(@RequestParam("signature")
    String signature, @RequestParam("timestamp")
    String timestamp, @RequestParam("nonce")
    String nonce, @RequestParam("echostr")
    String echostr, HttpServletResponse response) throws IOException {
        String token = templateService.getMessage("weixin.config.token");
        boolean isValidate = Tools.checkSignature(token, signature, timestamp, nonce);
        logger.info("WeiXin validate. signature:{0}, timestamp:{1}, nonce:{2}, isValidate:{3}", signature, timestamp, nonce, isValidate);
        if (isValidate) {
            response.getWriter().write(echostr);
            response.getWriter().flush();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dispatch", method = RequestMethod.POST)
    public void dispatch(@RequestParam("msg_signature")
    String msgSignature, @RequestParam("timestamp")
    String timestamp, @RequestParam("nonce")
    String nonce, @RequestParam("encrypt_type")
    String encryptType, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Document document = Tools.getDocumentByInputStream(request.getInputStream());

        WxRequest wxRequest = new WxRequest(templateService.getMessage("weixin.config.appid"), templateService.getMessage("weixin.config.encodingaeskey"), templateService.getMessage("weixin.config.token"), document, msgSignature, timestamp, nonce, encryptType);

        String reply = wxDispatchService.invoke(wxRequest);
        if (StringUtils.isNotBlank(reply)) {
            response.getWriter().write(reply);
            response.getWriter().flush();
        }

        WXBizMsgCrypt pc = new WXBizMsgCrypt(templateService.getMessage("weixin.config.token"), templateService.getMessage("weixin.config.encodingaeskey"), templateService.getMessage("weixin.config.appid"));
        String replyMsg = "<xml><ToUserName><![CDATA[o8mpLwBqFO8CALmQgn5MJf47zbvw]]></ToUserName><FromUserName><![CDATA[gh_9cdd71177503]]></FromUserName><CreateTime>"
                + String.valueOf(System.currentTimeMillis()).substring(0, 10) + "</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[萨的撒的你好]]></Content></xml>";

        String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
        response.getWriter().write(mingwen);
        response.getWriter().flush();

    }

    @RequestMapping("/auth")
    public String auth(@RequestParam(value = "code", required = false)
    String code, HttpSession session) {
        if (StringUtils.isNotBlank(code)) {
            WeiXin weixin = wxDispatchService.getAccessToken(code);
            if (ObjectUtils.notEqual(weixin, null)) {
                session.setAttribute("third", weixin);
                return "redirect:/login/";
            }
        }
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/generateOrderId")
    public Response generateOrderId(Order order, HttpServletRequest request) {
        order.setIp(WebUtils.getIp(request));
        return orderService.generateOrder(order);
    }

}
