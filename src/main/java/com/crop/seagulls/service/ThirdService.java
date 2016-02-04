package com.crop.seagulls.service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Third;

public interface ThirdService {

    public Response add(Third third);
    
    public Third findOne(Third third);
}
