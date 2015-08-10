package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.SupplyDAO;
import com.crop.seagulls.entities.Supply;
import com.crop.seagulls.service.SupplyService;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyDAO supplyDAO;

    @Override
    public Response add(Supply supply) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(supply);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            supplyDAO.save(supply);
            if (supply.getId() == null || supply.getId() <= 0L) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Response modifySupply(Supply supply) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(supply);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            int affect = supplyDAO.update(supply);
            if (affect <= 0) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Paging<Supply> querySupplies(Supply supply) {
        List<Supply> list = supplyDAO.findBuies(supply);
        Integer totalCount = supplyDAO.findBuiesCount(supply);
        return new Paging<Supply>(totalCount, supply.getPage(), supply.getPageSize(), list);
    }

    private Response checkValidate(Supply supply) {
        Response result = new Response();
        return result;
    }
}
