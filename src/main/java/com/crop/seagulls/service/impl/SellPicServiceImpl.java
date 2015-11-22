package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.SellPicDAO;
import com.crop.seagulls.entities.SellPic;
import com.crop.seagulls.service.SellPicService;

@Service
public class SellPicServiceImpl implements SellPicService {

    @Autowired
    private SellPicDAO sellPicDAO;

    @Override
    public List<SellPic> findBySupplyId(Long id) {
        return sellPicDAO.getBySell(id);
    }

    @Override
    public Response add(SellPic supplyPic) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        sellPicDAO.save(supplyPic);
        if (supplyPic.getId() == null || supplyPic.getId() <= 0L) {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

    @Override
    public List<SellPic> findAll() {
        return sellPicDAO.getAll();
    }

}
