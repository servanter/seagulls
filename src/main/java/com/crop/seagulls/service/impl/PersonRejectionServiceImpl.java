package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.PersonRejectionDAO;
import com.crop.seagulls.entities.PersonRejection;
import com.crop.seagulls.service.PersonRejectionService;

@Service
public class PersonRejectionServiceImpl implements PersonRejectionService {

    @Autowired
    private PersonRejectionDAO personRejectionDAO;

    @Override
    public Response add(PersonRejection rejection) {
        return personRejectionDAO.save(rejection) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response batchAdd(List<PersonRejection> rejections) {
        personRejectionDAO.batchSave(rejections);
        return new Response(ReturnCode.SUCCESS);
    }

}
