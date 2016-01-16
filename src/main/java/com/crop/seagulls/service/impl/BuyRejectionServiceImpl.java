package com.crop.seagulls.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.BuyRejectionDAO;
import com.crop.seagulls.entities.BuyRejection;
import com.crop.seagulls.entities.SellRejection;
import com.crop.seagulls.service.BuyRejectionService;

@Service
public class BuyRejectionServiceImpl implements BuyRejectionService {

    @Autowired
    private BuyRejectionDAO buyRejectionDAO;

    @Override
    public Response add(BuyRejection rejection) {
        return buyRejectionDAO.save(rejection) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response batchAdd(List<BuyRejection> rejections) {
        buyRejectionDAO.batchSave(rejections);
        return new Response(ReturnCode.SUCCESS);
    }

    @Override
    public Paging<BuyRejection> findList(BuyRejection buyRejection) {
        List<BuyRejection> rejections = buyRejectionDAO.getList(buyRejection);
        int total = rejections.size();
        if (buyRejection.getPageSize() > 1) {
            total = buyRejectionDAO.getListCount(buyRejection);
        }
        return new Paging<BuyRejection>(total, buyRejection.getPage(), buyRejection.getPageSize(), rejections);
    }

    @Override
    public BuyRejection findByInfoId(Long id) {
        BuyRejection rejection = new BuyRejection();
        rejection.setInfoId(id);
        List<BuyRejection> rejections = buyRejectionDAO.getList(rejection);
        if (CollectionUtils.isNotEmpty(rejections)) {
            return rejections.get(0);
        }
        return null;
    }

}
