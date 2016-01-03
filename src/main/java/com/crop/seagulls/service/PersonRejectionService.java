package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.PersonRejection;

public interface PersonRejectionService {

    public Response add(PersonRejection rejection);
    
    public Response batchAdd(List<PersonRejection> rejections);

    public PersonRejection findByAuthId(Long anthId);
}
