package com.crop.seagulls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.SellProductDAO;
import com.crop.seagulls.entities.SellProduct;
import com.crop.seagulls.service.SellProductService;

@Service
public class SellProductServiceImpl implements SellProductService {

    @Autowired
    private SellProductDAO sellProductDAO;

    @Override
    public Response add(SellProduct sellProduct) {
        return sellProductDAO.save(sellProduct) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS);
    }
}
