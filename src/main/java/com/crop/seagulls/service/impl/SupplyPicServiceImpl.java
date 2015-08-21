package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.SupplyPicDAO;
import com.crop.seagulls.entities.SupplyPic;
import com.crop.seagulls.service.SupplyPicService;

@Service
public class SupplyPicServiceImpl implements SupplyPicService {

    @Autowired
    private SupplyPicDAO supplyPicDAO;

    @Override
    public List<SupplyPic> queryBySupplyId(Long id) {
        return supplyPicDAO.findBySupplyId(id);
    }

    @Override
    public Response add(SupplyPic supplyPic) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        supplyPicDAO.save(supplyPic);
        if (supplyPic.getId() == null || supplyPic.getId() <= 0L) {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

}
