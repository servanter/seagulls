package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.SupplyPic;

/**
 * Product supply pic serivce
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface SupplyPicService {

    /**
     * Query by supply id
     * 
     * @param id
     * @return
     */
    public List<SupplyPic> queryBySupplyId(Long id);

    /**
     * Add supply pic
     * 
     * @param supplyPic
     * @return
     */
    public Response add(SupplyPic supplyPic);
}
