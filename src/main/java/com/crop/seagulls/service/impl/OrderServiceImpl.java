package com.crop.seagulls.service.impl;

import java.io.StringReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.ConsumeOrderPayWayEnum;
import com.crop.seagulls.bean.ConsumeOrderSourceEnum;
import com.crop.seagulls.bean.ConsumeOrderStatusEnum;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.bean.Src;
import com.crop.seagulls.dao.ConsumeOrderDAO;
import com.crop.seagulls.entities.Address;
import com.crop.seagulls.entities.ConsumeOrder;
import com.crop.seagulls.entities.Sell;
import com.crop.seagulls.entities.SellProduct;
import com.crop.seagulls.entities.Third;
import com.crop.seagulls.service.AddressService;
import com.crop.seagulls.service.OrderService;
import com.crop.seagulls.service.SellProductService;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.ThirdService;
import com.crop.seagulls.util.CommonUtils;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.HttpUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.MD5Util;
import com.crop.seagulls.util.NumberUtils;
import com.crop.seagulls.util.RandomUtils;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ThirdService thirdService;

    @Autowired
    private SellProductService sellProductService;

    @Autowired
    private SellService sellService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ConsumeOrderDAO consumeOrderDAO;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public Response payOrder(ConsumeOrder order) {
        Response response = new Response(ReturnCode.ERROR);
        List<Pair<String, String>> pairs = new ArrayList<Pair<String, String>>();

        ConsumeOrder consumeOrder = consumeOrderDAO.getByOrderIdAndUserId(order.getId(), order.getUserId());
        if (consumeOrder != null) {

            String[] args = new String[11];
            switch (ConsumeOrderPayWayEnum.code2PayType(order.getPayType())) {
            case WEIXIN:
                String appId = templateService.getMessage("weixin.config.appid");
                String merchant = templateService.getMessage("weixin.config.merchant");
                String randomStr = RandomUtils.generateString(32);
                String info = "infoinfoinfoinfoinfo";

                // open id
                Third third = new Third();
                third.setUserId(order.getUserId());
                third.setSrc(Src.WEIXIN.getType());
                Third findThird = thirdService.findOne(third);
                if (ObjectUtils.notEqual(findThird, null)) {
                    String openId = findThird.getThirdUnionId();

                    pairs.add(Pair.of("appid", appId));
                    pairs.add(Pair.of("mch_id", merchant));
                    pairs.add(Pair.of("nonce_str", randomStr));
                    pairs.add(Pair.of("body", info));
                    pairs.add(Pair.of("out_trade_no", order.getId()));
                    pairs.add(Pair.of("total_fee", String.valueOf(consumeOrder.getPrice())));
                    pairs.add(Pair.of("trade_type", "JSAPI"));
                    pairs.add(Pair.of("spbill_create_ip", consumeOrder.getIp()));
                    pairs.add(Pair.of("openid", openId));
                    pairs.add(Pair.of("notify_url", templateService.getMessage("weixin.config.pay.callback.url")));

                    args[0] = appId;
                    args[1] = merchant;
                    args[2] = randomStr;
                    args[3] = info;
                    args[4] = order.getId();
                    args[5] = String.valueOf(consumeOrder.getPrice());
                    args[6] = "JSAPI";
                    args[7] = consumeOrder.getIp();
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

                    if (ReturnCode.isSuccess(response.getReturnCode())) {
                        order.setPayTime(new Timestamp(System.currentTimeMillis()));
                        int affect = consumeOrderDAO.update(order);
                        if (affect <= 0) {
                            response.setReturnCode(ReturnCode.ERROR);
                        }
                    }

                    // response page
                    if (ReturnCode.isSuccess(response.getReturnCode())) {
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
                break;
            default:
                break;
            }
        }

        logger.info("Order response:[{0}]", response);
        return response;

    }

    @Override
    public Response submitSellProduct(SellProduct sellProduct) {
        Response response = new Response(ReturnCode.SUCCESS);

        // fill in address
        if (!NumberUtils.isValidateNumber(sellProduct.getAddressId())) {
            response.setReturnCode(ReturnCode.PARAM_NOT_FOUND);
        } else {
            Address address = addressService.findUserAddress(sellProduct.getAddressId(), sellProduct.getUserId());
            if (ObjectUtils.equals(address, null)) {
                response.setReturnCode(ReturnCode.PARAM_NOT_FOUND);
            } else {
                sellProduct.setProvinceId(address.getProvinceId());
                sellProduct.setCityId(address.getCityId());
                sellProduct.setAreaId(address.getAreaId());
                sellProduct.setAddress(address.getAddress());
                sellProduct.setContactName(address.getContactName());
                sellProduct.setContactPhone(address.getContactPhone());
            }
        }

        if (ReturnCode.isSuccess(response.getReturnCode())) {
            Integer num = sellProduct.getNum();
            if (!NumberUtils.isMoreThanZero(num)) {
                response.setReturnCode(ReturnCode.ORDER_SELL_PRODUCT_NUM_UNVALID);
            }

            if (ReturnCode.isSuccess(response.getReturnCode())) {
                Long sellId = sellProduct.getSellId();
                if (!NumberUtils.isMoreThanZero(sellId)) {
                    response.setReturnCode(ReturnCode.ORDER_SELL_PRODUCT_NUM_UNVALID);
                }

                int price = 0;
                if (ReturnCode.isSuccess(response.getReturnCode())) {
                    Sell sell = sellService.findBaseInfoById(sellProduct.getSellId());
                    if (ObjectUtils.equals(sell, null)) {
                        response.setReturnCode(ReturnCode.ORDER_SELL_PRODUCT_SELL_ID_NOT_FOUND);
                    } else {
                        price = (int) (sell.getPrice() * 10 * 10);
                    }
                }

                int totalPrice = 0;
                if (ReturnCode.isSuccess(response.getReturnCode()) && price > 0) {
                    sellProduct.setPrice(price);
                    totalPrice = price * num;
                    sellProduct.setTotalPrice(totalPrice);
                    response = sellProductService.add(sellProduct);
                }

                if (ReturnCode.isSuccess(response.getReturnCode()) && price > 0) {

                    ConsumeOrder consumeOrder = new ConsumeOrder();
                    String orderId = DateUtils.getCurDate("yyyyMMdd") + "_" + System.currentTimeMillis() + "_" + RandomUtils.generateNumberString(6);
                    consumeOrder.setId(orderId);
                    consumeOrder.setInnerOrderId(sellProduct.getId());
                    consumeOrder.setSource(ConsumeOrderSourceEnum.SELL_PRODUCT.getCode());
                    consumeOrder.setUserId(sellProduct.getUserId());
                    consumeOrder.setStatus(ConsumeOrderStatusEnum.NOT_PAY.getCode());
                    consumeOrder.setPayType(ConsumeOrderPayWayEnum.DEFAULT.getCode());
                    consumeOrder.setPrice(totalPrice);
                    consumeOrder.setIp(sellProduct.getIp());
                    if (consumeOrderDAO.save(consumeOrder) > 0) {
                        response.setResult(orderId);
                    } else {
                        response.setReturnCode(ReturnCode.ERROR);
                    }
                }

            }
        }

        return response;
    }

    @Override
    public Map<String, Object> payConsumeOrderPre(ConsumeOrder consumeOrder) {
        Map<String, Object> result = new HashMap<String, Object>();
        ConsumeOrder order = consumeOrderDAO.getByOrderIdAndUserId(consumeOrder.getId(), consumeOrder.getUserId());
        if (ObjectUtils.notEqual(order, null)) {

            SellProduct sellProduct = sellProductService.findById(order.getInnerOrderId());
            Sell sell = sellService.findBaseInfoById(sellProduct.getSellId());
            String selectAddr = commonUtils.addAddr(sellProduct.getProvinceId(), sellProduct.getCityId(), sellProduct.getAreaId());

            result.put("sell", sell);
            result.put("order", order);
            result.put("sellProduct", sellProduct);
            result.put("address", selectAddr + " " + sellProduct.getAddress());
            result.put("totalPrice", NumberUtils.formatPrice((double) order.getPrice() / 100));

        }
        return result;
    }

    @Override
    public Map<String, Object> findConsumeOrder(ConsumeOrder consumeOrder) {
        Map<String, Object> result = new HashMap<String, Object>();
        ConsumeOrder order = consumeOrderDAO.getByOrderIdAndUserId(consumeOrder.getId(), consumeOrder.getUserId());
        if (ObjectUtils.notEqual(order, null)) {

            SellProduct sellProduct = sellProductService.findById(order.getInnerOrderId());
            Sell sell = sellService.findBaseInfoById(sellProduct.getSellId());
            String selectAddr = commonUtils.addAddr(sellProduct.getProvinceId(), sellProduct.getCityId(), sellProduct.getAreaId());

            result.put("sell", sell);
            result.put("order", order);
            result.put("sellProduct", sellProduct);
            result.put("address", selectAddr + " " + sellProduct.getAddress());
            result.put("totalPrice", NumberUtils.formatPrice((double) order.getPrice() / 100));
            result.put("payWay", ConsumeOrderPayWayEnum.code2PayType(order.getPayType()));
        }
        return result;
    }

    @Override
    public Response confirmConsumeOrder(ConsumeOrder consumeOrder) {
        Response response = new Response(ReturnCode.ERROR);
        ConsumeOrder order = consumeOrderDAO.getByOrderIdAndUserId(consumeOrder.getId(), consumeOrder.getUserId());
        if (ObjectUtils.notEqual(order, null)) {
            consumeOrder.setStatus(ConsumeOrderStatusEnum.USER_CONFIRM.getCode());
            if (consumeOrderDAO.update(consumeOrder) > 0) {
                response.setReturnCode(ReturnCode.SUCCESS);
                response.setResult(consumeOrder.getId());
            }
        }
        return response;
    }

    @Override
    public Map<String, Object> paySuccess(ConsumeOrder consumeOrder) {
        Map<String, Object> result = new HashMap<String, Object>();
        ConsumeOrder order = consumeOrderDAO.getByOrderIdAndUserId(consumeOrder.getId(), consumeOrder.getUserId());
        if (ObjectUtils.notEqual(order, null)) {

            SellProduct sellProduct = sellProductService.findById(order.getInnerOrderId());
            Sell sell = sellService.findBaseInfoById(sellProduct.getSellId());

            result.put("sell", sell);
            result.put("order", order);
            result.put("sellProduct", sellProduct);
            result.put("totalPrice", NumberUtils.formatPrice((double) order.getPrice() / 100));
        }
        return result;
    }

    @Override
    public Map<String, Object> payFail(ConsumeOrder consumeOrder) {
        Map<String, Object> result = new HashMap<String, Object>();
        ConsumeOrder order = consumeOrderDAO.getByOrderIdAndUserId(consumeOrder.getId(), consumeOrder.getUserId());
        if (ObjectUtils.notEqual(order, null)) {

            SellProduct sellProduct = sellProductService.findById(order.getInnerOrderId());
            Sell sell = sellService.findBaseInfoById(sellProduct.getSellId());

            result.put("sell", sell);
            result.put("order", order);
            result.put("sellProduct", sellProduct);
            result.put("totalPrice", NumberUtils.formatPrice((double) order.getPrice() / 100));
        }
        return result;
    }

    @Override
    public Paging<Map<String, Object>> findMyOrder(ConsumeOrder order) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<ConsumeOrder> orders = consumeOrderDAO.getList(order);
        int total = consumeOrderDAO.getListCount(order);

        if (CollectionUtils.isNotEmpty(orders)) {
            for (ConsumeOrder consumeOrder : orders) {
                Map<String, Object> every = new HashMap<String, Object>();
                SellProduct sellProduct = sellProductService.findById(consumeOrder.getInnerOrderId());
                Sell sell = sellService.findBaseInfoById(sellProduct.getSellId());
                every.put("sell", sell);
                every.put("order", consumeOrder);
                every.put("sellProduct", sellProduct);
                every.put("totalPrice", NumberUtils.formatPrice((double) consumeOrder.getPrice() / 100));
                result.add(every);
            }
        }

        return new Paging<Map<String, Object>>(total, order.getPage(), order.getPageSize(), result);
    }

    @Override
    public Response ajaxFindMyOrder(ConsumeOrder order) {
        Response response = new Response(ReturnCode.SUCCESS);
        Map<String, Object> result = new HashMap<String, Object>();
        if (ObjectUtils.equals(order.getPage(), null)) {
            order.setPage(1);
        }
        Paging<Map<String, Object>> paging = findMyOrder(order);
        if (ObjectUtils.notEqual(paging, null)) {
            List<Map<String, Object>> data = paging.getResult();
            if (CollectionUtils.isEmpty(data)) {
                response.setReturnCode(ReturnCode.NO_MORE_PAGE);
            } else {
                result.put("nextPage", order.getPage() + 1);
            }
            result.put("list", paging.getResult());
        }
        response.setResult(result);
        return response;
    }
}
