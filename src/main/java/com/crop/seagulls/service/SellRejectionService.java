package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.SellRejection;

public interface SellRejectionService {

    Response add(SellRejection rejection);

    Response batchAdd(List<SellRejection> rejections);

    public Paging<SellRejection> findList(SellRejection sellRejection);
    
    public SellRejection findByInfoId(Long infoId);

}
