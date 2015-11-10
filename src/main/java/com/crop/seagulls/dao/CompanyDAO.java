package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Company;

@Repository
public interface CompanyDAO {

    int save(Company company);

    List<Company> getList(Company company);

    int getListCount(Company company);

}
