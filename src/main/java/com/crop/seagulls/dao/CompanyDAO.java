package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserCompany;

@Repository
public interface CompanyDAO {

    int save(Company company);

    List<Company> getList(Company company);

    int getListCount(Company company);

    List<Company> getByUserId(User user);

    Integer getByUserIdCount(User user);

    int update(Company company);
    
    Long saveUserCompany(UserCompany userCompany);

}
