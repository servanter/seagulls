package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.BuyDAO;
import com.crop.seagulls.entities.Buy;
import com.crop.seagulls.service.BuyService;

@Service
public class BuyServiceImpl implements BuyService {

    @Autowired
    private BuyDAO buyDAO;

    @Override
    public Response add(Buy buy) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(buy);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            buyDAO.save(buy);
            if (buy.getId() == null || buy.getId() <= 0L) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Response modifyBuy(Buy buy) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(buy);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            int affect = buyDAO.update(buy);
            if (affect <= 0) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Paging<Buy> queryBuies(Buy buy) {
        List<Buy> list = buyDAO.findBuies(buy);
        Integer totalCount = buyDAO.findBuiesCount(buy);
        return new Paging<Buy>(totalCount, buy.getPage(), buy.getPageSize(), list);
    }

    private Response checkValidate(Buy buy) {
        Response response = new Response();
        return response;
    }

}
