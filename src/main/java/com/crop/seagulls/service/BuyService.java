package com.crop.seagulls.service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Buy;

/**
 * Buy service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface BuyService {

    /**
     * Add buy
     * 
     * @param buy
     * @return
     */
    public Response add(Buy buy);

    /**
     * Query buies
     * 
     * @param buy
     *            search model
     * @return
     */
    public Paging<Buy> queryBuies(Buy buy);

    /**
     * Modify buy
     * 
     * @param buy
     * @return
     */
    public Response modifyBuy(Buy buy);
}
