package com.crop.seagulls.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.SellRejectionDAO;
import com.crop.seagulls.entities.SellRejection;
import com.crop.seagulls.service.SellRejectionService;

@Service
public class SellRejectionServiceImpl implements SellRejectionService {

    @Autowired
    private SellRejectionDAO sellRejectionDAO;

    @Override
    public Response add(SellRejection rejection) {
        return sellRejectionDAO.save(rejection) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response batchAdd(List<SellRejection> rejections) {
        sellRejectionDAO.batchSave(rejections);
        return new Response(ReturnCode.SUCCESS);
    }

    @Override
    public Paging<SellRejection> findList(SellRejection rejection) {
        List<SellRejection> rejections = sellRejectionDAO.getList(rejection);
        int total = rejections.size();
        if (rejection.getPageSize() > 1) {
            total = sellRejectionDAO.getListCount(rejection);
        }
        return new Paging<SellRejection>(total, rejection.getPage(), rejection.getPageSize(), rejections);
    }

    @Override
    public SellRejection findByInfoId(Long infoId) {
        SellRejection rejection = new SellRejection();
        rejection.setInfoId(infoId);
        List<SellRejection> rejections = sellRejectionDAO.getList(rejection);
        if (CollectionUtils.isNotEmpty(rejections)) {
            return rejections.get(0);
        }
        return null;
    }

}
