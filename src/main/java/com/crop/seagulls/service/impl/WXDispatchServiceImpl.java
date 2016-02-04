package com.crop.seagulls.service.impl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.third.WeiXin;
import com.crop.seagulls.bean.wx.WxRequest;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.WXDispatchService;
import com.crop.seagulls.util.HttpUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.weixin.WXBizMsgCrypt;

@Service
public class WXDispatchServiceImpl implements WXDispatchService {

    private static Logger logger = Logger.getLogger(WXDispatchServiceImpl.class);
    @Autowired
    private TemplateService templateService;

    @Override
    public String invoke(WxRequest wxRequest) {
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(templateService.getMessage("weixin.config.token"), templateService.getMessage("weixin.config.encodingaeskey"), templateService.getMessage("weixin.config.appid"));

        } catch (Exception e) {
        }

        return null;
    }

    @Override
    public WeiXin getAccessToken(String code) {
        WeiXin weiXin = new WeiXin();
        String url = templateService.getMessage("weixin.config.getaccesstoken.url", templateService.getMessage("weixin.config.appid"), templateService.getMessage("weixin.config.appsecret"), code);
        try {
            String result = HttpUtils.httpGet(url);
            logger.info("Weixin get result:[{0}]", result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            String accessToken = jsonObject.getString("access_token");
            String expireIn = jsonObject.getString("expires_in");
            String refreshToken = jsonObject.getString("refresh_token");
            String openId = jsonObject.getString("openid");
            weiXin.setOpenId(openId);
            weiXin.setAccessToken(accessToken);
            weiXin.setRefreshToken(refreshToken);
            weiXin.setExpireIn(expireIn);
            logger.info("WeiXin result:[{0}]", weiXin);
        } catch (Exception e) {
            logger.error("Weixin GETACCESSTOKENERROR url:[{0}]", e, url);
        }
        return weiXin;
    }


}
