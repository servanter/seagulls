package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.BuyRejection;

public interface BuyRejectionService {

    Response add(BuyRejection rejection);

    Response batchAdd(List<BuyRejection> rejections);

    public Paging<BuyRejection> findList(BuyRejection buyRejection);

    BuyRejection findByInfoId(Long id);

}
