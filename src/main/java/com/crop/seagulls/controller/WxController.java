package com.crop.seagulls.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.Tools;

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
    public String dispatch() {
        return null;
    }
}
