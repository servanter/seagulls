package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.CompanyRejection;

public interface CompanyRejectionService {

    public Response add(CompanyRejection rejection);
    
    public Response batchAdd(List<CompanyRejection> rejections);

    public CompanyRejection findByCompanyId(Long id);
}
