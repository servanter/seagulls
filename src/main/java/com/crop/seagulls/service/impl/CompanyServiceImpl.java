package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.AdminUserCache;
import com.crop.seagulls.dao.CompanyDAO;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.CompanyRejection;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserCompany;
import com.crop.seagulls.service.CompanyRejectionService;
import com.crop.seagulls.service.CompanyService;
import com.crop.seagulls.util.NumberUtils;
import com.crop.seagulls.util.SecurityUtils;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private AdminUserCache adminUserCache;
    
    @Autowired
    private CompanyRejectionService companyRejectionService;

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
        if (CollectionUtils.isNotEmpty(result)) {
            for (Company model : result) {
                if (model.getAuditId() > 0 && ObjectUtils.notEqual(adminUserCache.getById(model.getAuditId()), null)) {
                    model.setAuditName(adminUserCache.getById(model.getAuditId()).getUsername());
                }
            }
        }
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

    @Override
    public List<Company> findAll() {
        return companyDAO.getAll();
    }

    @Override
    public Response pass(Long id) {
        Company company = new Company();
        company.setId(id);
        company.setStatus(CommonStatus.PASS.getCode());
        company.setAuditId(SecurityUtils.getLoginedUserId());
        return companyDAO.update(company) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response reject(Long id, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        Company company = new Company();
        company.setId(id);
        company.setStatus(CommonStatus.REJECT.getCode());
        company.setAuditId(SecurityUtils.getLoginedUserId());
        response = companyDAO.update(company) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            CompanyRejection rejection = new CompanyRejection();
            rejection.setAuditId(SecurityUtils.getLoginedUserId());
            rejection.setAuthId(id);
            rejection.setOpinion(opinion);
            rejection.setType(type);
            response = companyRejectionService.add(rejection);
        }
        return response;
    }

    @Override
    public Response passAll(String ids) {
        Company company = new Company();
        company.setStatus(CommonStatus.PASS.getCode());
        company.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        return companyDAO.batchUpdate(company, id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response rejectAll(String ids, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        Company company = new Company();
        company.setStatus(CommonStatus.REJECT.getCode());
        company.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        response = companyDAO.batchUpdate(company, id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            List<CompanyRejection> rejections = new ArrayList<CompanyRejection>();
            for (Long everyId : id) {
                CompanyRejection rejection = new CompanyRejection();
                rejection.setAuditId(SecurityUtils.getLoginedUserId());
                rejection.setAuthId(everyId);
                rejection.setOpinion(opinion);
                rejection.setType(type);
                rejections.add(rejection);
            }
            response = companyRejectionService.batchAdd(rejections);
        }
        return response;
    }

    @Override
    public Company findById(Long id) {
        return companyDAO.getById(id);
    }

}
