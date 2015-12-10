package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.BuyPic;

/**
 * Product supply pic serivce
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface BuyPicService {

    public List<BuyPic> findAll();

    /**
     * 
     * Query by supply id
     * 
     * @param id
     * @return
     */
    public List<BuyPic> findByBuyId(Long id);

    /**
     * Add supply pic
     * 
     * @param supplyPic
     * @return
     */
    public Response add(BuyPic buyPic);

    public List<BuyPic> findPicsById(Long picId);
}
