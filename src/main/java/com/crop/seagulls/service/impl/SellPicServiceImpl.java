package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
    public List<SellPic> findBySellId(Long id) {
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

    @Override
    public List<SellPic> findPicsById(Long picId) {
        List<SellPic> result = new ArrayList<SellPic>();
        SellPic pic = new SellPic();
        pic.setId(picId);
        List<SellPic> pics = sellPicDAO.getList(pic);
        if (CollectionUtils.isNotEmpty(pics)) {
            result = findBySellId(pics.get(0).getSellId());
        }
        return result;
    }

    @Override
    public Response modify(SellPic sellPic) {
        return sellPicDAO.update(sellPic) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
