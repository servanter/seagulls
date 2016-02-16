package com.crop.seagulls.service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Order;
import com.crop.seagulls.entities.SellProduct;

public interface OrderService {

    public Response generateOrder(Order order);

    public Response submitSellProduct(SellProduct sellProduct);
}
