package com.crop.seagulls.service;

import java.util.Map;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.ConsumeOrder;
import com.crop.seagulls.entities.SellProduct;

public interface OrderService {

    public Response payOrder(ConsumeOrder order);

    public Response submitSellProduct(SellProduct sellProduct);

    public Map<String, Object> payConsumeOrderPre(ConsumeOrder consumeOrder);

    public Map<String, Object> findConsumeOrder(ConsumeOrder consumeOrder);

    public Response confirmConsumeOrder(ConsumeOrder consumeOrder);

    public Map<String, Object> paySuccess(ConsumeOrder consumeOrder);
    
    public Map<String, Object> payFail(ConsumeOrder consumeOrder);
    
    
    public Paging<Map<String, Object>> findMyOrder(ConsumeOrder order);
    
    public Response ajaxFindMyOrder(ConsumeOrder order);
}
