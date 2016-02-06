package com.crop.seagulls.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.PayTypeEnum;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.bean.Src;
import com.crop.seagulls.dao.OrderDAO;
import com.crop.seagulls.entities.Order;
import com.crop.seagulls.entities.Third;
import com.crop.seagulls.service.OrderService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.ThirdService;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.HttpUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.MD5Util;
import com.crop.seagulls.util.RandomUtils;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ThirdService thirdService;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Response generateOrder(Order order) {
        Response response = new Response(ReturnCode.ERROR);
        List<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();

        String orderId = DateUtils.getCurDate("yyyyMMdd") + "_" + System.currentTimeMillis() + "_" + RandomUtils.generateNumberString(6);
        order.setId(orderId);
        int affect = orderDAO.save(order);
        if (affect > 0) {

            String[] args = new String[11];
            switch (PayTypeEnum.code2PayType(order.getPayType())) {
            case WEIXIN:
                String appId = templateService.getMessage("weixin.config.appid");
                String merchant = templateService.getMessage("weixin.config.merchant");
                String randomStr = RandomUtils.generateString(32);
                String info = "infoinfoinfoinfoinfo";

                // open id
                String openId = StringUtils.EMPTY;
                Third third = new Third();
                third.setUserId(order.getUserId());
                third.setSrc(Src.WEIXIN.getType());
                Third findThird = thirdService.findOne(third);
                if (ObjectUtils.notEqual(findThird, null)) {
                    openId = findThird.getMetaIndex1();
                }

                pairs.add(Pair.of("appid", appId));
                pairs.add(Pair.of("mch_id", merchant));
                pairs.add(Pair.of("nonce_str", randomStr));
                pairs.add(Pair.of("body", info));
                pairs.add(Pair.of("out_trade_no", orderId));
                pairs.add(Pair.of("total_fee", String.valueOf(order.getPrice())));
                pairs.add(Pair.of("trade_type", "JSAPI"));
                pairs.add(Pair.of("spbill_create_ip", order.getIp()));
                pairs.add(Pair.of("openid", openId));
                pairs.add(Pair.of("notify_url", templateService.getMessage("weixin.config.pay.callback.url")));

                args[0] = appId;
                args[1] = merchant;
                args[2] = randomStr;
                args[3] = info;
                args[4] = orderId;
                args[5] = String.valueOf(order.getPrice());
                args[6] = "JSAPI";
                args[7] = order.getIp();
                args[8] = templateService.getMessage("weixin.config.pay.callback.url");
                args[9] = openId;

                Collections.sort(pairs, new Comparator<Pair<String, String>>() {

                    @Override
                    public int compare(Pair<String, String> o1, Pair<String, String> o2) {
                        return o1.getLeft().compareTo(o2.getLeft());
                    }
                });
                String rawString = StringUtils.EMPTY;
                StringBuilder rawBuilder = new StringBuilder();
                for (Pair<String, String> pair : pairs) {
                    rawBuilder.append(pair.getLeft() + "=" + pair.getRight() + "&");
                }

                if (rawBuilder.length() > 0) {
                    rawString = rawBuilder.substring(0, rawBuilder.length() - 1);
                }
                rawString = rawString + "&key=" + templateService.getMessage("weixin.pay.merchant.key");
                logger.info("Weixin GETORDER md5 raw:[{0}]", rawString);
                String sign = MD5Util.generateMD5Str(rawString, "").toUpperCase();
                args[10] = sign;

                String data = templateService.getMessage("weixin.pay.generate.order.xml", args);

                logger.info("Weixin GETORDERPOSTDATA :[{0}]", data);
                String result = HttpUtils.httpPost(templateService.getMessage("weixin.pay.unifiedorder.url"), data);
                logger.info("Weixin unified order result[{0}]", result);

                StringReader re = new StringReader(result);
                Document document = null;
                try {
                    SAXReader reader = new SAXReader();
                    document = reader.read(re);
                } catch (DocumentException e) {
                }

                Element root = document.getRootElement();

                if (root.element("return_code") != null) {
                    order.setMetaIndex1(root.element("return_code").getText());
                }
                if (root.element("return_msg") != null) {
                    order.setMetaIndex2(root.element("return_msg").getText());
                }

                if (root.element("return_code") != null && root.element("return_code").getText().equals("SUCCESS") && root.element("result_code") != null
                        && root.element("result_code").getText().equals("SUCCESS")) {
                    String prepayId = root.element("prepay_id").getText();
                    order.setMetaIndex3(prepayId);
                    response.setReturnCode(ReturnCode.SUCCESS);
                }
                break;
            default:
                break;
            }
        }

        if (ReturnCode.isSuccess(response.getReturnCode())) {
            affect = orderDAO.update(order);
            if (affect <= 0) {
                response.setReturnCode(ReturnCode.ERROR);
            } else {
                Map<String, Object> orderInfo = new LinkedHashMap<String, Object>();
                String nonceStr = RandomUtils.generateString(32);
                orderInfo.put("appId", templateService.getMessage("weixin.config.appid"));
                orderInfo.put("nonceStr", nonceStr);
                orderInfo.put("package", "prepay_id=" + order.getMetaIndex3());
                orderInfo.put("signType", "MD5");
                String currentTimeMillis = String.valueOf(System.currentTimeMillis());
                orderInfo.put("timeStamp", currentTimeMillis.substring(0, currentTimeMillis.length() - 3));

                StringBuilder paySignBuilder = new StringBuilder();
                for (String key : orderInfo.keySet()) {
                    paySignBuilder.append(key + "=" + orderInfo.get(key) + "&");
                }
                String paySign = StringUtils.EMPTY;
                if (paySignBuilder.length() > 0) {
                    paySign = paySignBuilder.substring(0, paySignBuilder.length() - 1);
                }
                paySign = paySign + "&key=" + templateService.getMessage("weixin.pay.merchant.key");
                logger.info("Weixin GENERATEPAYSIGN raw md5:{0}", paySign);
                paySign = MD5Util.generateMD5Str(paySign, "").toUpperCase();
                orderInfo.put("paySign", paySign);
                response.setResult(orderInfo);

            }
        }
        logger.info("Order response:[{0}]", response);
        return response;

    }
}
