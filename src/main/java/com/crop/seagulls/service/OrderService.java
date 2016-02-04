package com.crop.seagulls.service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Order;

public interface OrderService {

    public Response generateOrder(Order order);
}
