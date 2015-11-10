package com.crop.seagulls.service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Company;

public interface CompanyService {

    public Paging<Company> findList(Company company);
    
    public Response add(Company company);
    
}
