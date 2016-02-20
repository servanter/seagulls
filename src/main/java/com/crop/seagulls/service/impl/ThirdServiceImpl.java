package com.crop.seagulls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.ThirdDAO;
import com.crop.seagulls.entities.Third;
import com.crop.seagulls.service.ThirdService;

@Service
public class ThirdServiceImpl implements ThirdService {

    @Autowired
    private ThirdDAO thirdDAO;

    @Override
    public Response add(Third third) {
        return thirdDAO.save(third) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Third findOne(Third third) {
        return thirdDAO.getByThirdUnionId(third);
    }
}
