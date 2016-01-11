package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.BuyPicDAO;
import com.crop.seagulls.entities.BuyPic;
import com.crop.seagulls.service.BuyPicService;

@Service
public class BuyPicServiceImpl implements BuyPicService {

    @Autowired
    private BuyPicDAO buyPicDAO;

    @Override
    public List<BuyPic> findByBuyId(Long id) {
        return buyPicDAO.getByBuy(id);
    }

    @Override
    public Response add(BuyPic buyPic) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        buyPicDAO.save(buyPic);
        if (buyPic.getId() == null || buyPic.getId() <= 0L) {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

    @Override
    public List<BuyPic> findAll() {
        return buyPicDAO.getAll();
    }

    @Override
    public List<BuyPic> findPicsById(Long picId) {
        List<BuyPic> result = new ArrayList<BuyPic>();
        BuyPic pic = new BuyPic();
        pic.setId(picId);
        List<BuyPic> pics = buyPicDAO.getList(pic);
        if (CollectionUtils.isNotEmpty(pics)) {
            result = findByBuyId(pics.get(0).getBuyId());
        }
        return result;
    }

    @Override
    public Response modify(BuyPic buyPic) {
        return buyPicDAO.update(buyPic) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
