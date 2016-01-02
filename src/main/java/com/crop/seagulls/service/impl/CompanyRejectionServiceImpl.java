package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.CompanyRejectionDAO;
import com.crop.seagulls.entities.CompanyRejection;
import com.crop.seagulls.service.CompanyRejectionService;

@Service
public class CompanyRejectionServiceImpl implements CompanyRejectionService {

    @Autowired
    private CompanyRejectionDAO companyRejectionDAO;

    @Override
    public Response add(CompanyRejection rejection) {
        return companyRejectionDAO.save(rejection) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response batchAdd(List<CompanyRejection> rejections) {
        companyRejectionDAO.batchSave(rejections);
        return new Response(ReturnCode.SUCCESS);
    }

}
