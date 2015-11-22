package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.SellPic;

/**
 * Product supply pic serivce
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface SellPicService {

    public List<SellPic> findAll();

    /**
     * 
     * Query by supply id
     * 
     * @param id
     * @return
     */
    public List<SellPic> findBySupplyId(Long id);

    /**
     * Add supply pic
     * 
     * @param supplyPic
     * @return
     */
    public Response add(SellPic supplyPic);
}
