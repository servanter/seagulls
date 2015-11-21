package com.crop.seagulls.service.impl;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.CompanyDAO;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserCompany;
import com.crop.seagulls.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public Response add(Company company) {
        Response response = new Response(ReturnCode.SUCCESS);
        int affect = companyDAO.save(company);
        if (affect > 0) {
            UserCompany userCompany = new UserCompany();
            userCompany.setUserId(company.getUserId());
            userCompany.setCompanyId(company.getId());
            userCompany.setStatus(CommonStatus.PASS.getCode());
            response = companyDAO.saveUserCompany(userCompany) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        } else {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

    @Override
    public Paging<Company> findList(Company company) {
        List<Company> result = companyDAO.getList(company);
        int total = companyDAO.getListCount(company);
        return new Paging<Company>(total, company.getPage(), company.getPageSize(), result);
    }

    @Override
    public Paging<Company> findByUserId(User user) {
        List<Company> companies = companyDAO.getByUserId(user);
        Integer total = companyDAO.getByUserIdCount(user);
        return new Paging<Company>(total, user.getPage(), user.getPageSize(), companies);
    }

    @Override
    public Response modify(Company company) {
        return companyDAO.update(company) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
