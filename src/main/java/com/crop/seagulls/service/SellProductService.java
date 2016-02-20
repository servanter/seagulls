package com.crop.seagulls.service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.SellProduct;

public interface SellProductService {

    public Response add(SellProduct sellProduct);

    public SellProduct findById(Long innerOrderId);
    
}
