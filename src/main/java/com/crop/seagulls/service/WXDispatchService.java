package com.crop.seagulls.service;

import com.crop.seagulls.bean.third.WeiXin;
import com.crop.seagulls.bean.wx.WxRequest;

public interface WXDispatchService {

    String invoke(WxRequest wxRequest);

    WeiXin getAccessToken(String code);

}
