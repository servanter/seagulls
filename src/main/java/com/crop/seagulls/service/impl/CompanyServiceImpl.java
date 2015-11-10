package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.CompanyDAO;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public Response add(Company company) {
        return companyDAO.save(company) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Paging<Company> findList(Company company) {
        List<Company> result = companyDAO.getList(company);
        int total = companyDAO.getListCount(company);
        return new Paging<Company>(total, company.getPage(), company.getPageSize(), result);
    }

}
