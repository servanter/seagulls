package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.User;

public interface CompanyService {

    public Paging<Company> findList(Company company);

    public Response add(Company company);

    public Paging<Company> findByUserId(User user);

    public Response modify(Company company);

    public List<Company> findAll();

}
