package com.crop.seagulls.task;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crop.seagulls.common.Constant;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.HttpUtils;

@Component
public class WxTask {

    @Autowired
    private TemplateService templateService;

    @Scheduled(cron = "0 0 * * * ?")
    public void refreshToken() {
        String appId = templateService.getMessage("weixin.config.appid");
        String secret = templateService.getMessage("weixin.config.appsecret");
        String url = templateService.getMessage("weixin.refresh.url", appId, secret);

        String result = HttpUtils.httpGet(url);
        if (StringUtils.isNotBlank(result)) {
            JSONObject jsonObject = JSONObject.fromObject(result);
            if (jsonObject.containsKey("access_token")) {
                Constant.WX_TOKEN = jsonObject.getString("access_token");
            }
        }
    }

}
