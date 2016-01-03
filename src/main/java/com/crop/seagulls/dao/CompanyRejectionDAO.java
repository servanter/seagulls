package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.CompanyRejection;

@Repository
public interface CompanyRejectionDAO {

    int save(CompanyRejection rejection);

    void batchSave(List<CompanyRejection> rejections);

    CompanyRejection getByCompanyId(Long id);

}
