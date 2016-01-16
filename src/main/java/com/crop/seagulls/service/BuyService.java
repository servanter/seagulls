package com.crop.seagulls.service;

import java.util.List;
import java.util.Map;

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
    public Response add(Buy buy, List<String> picUrls);

    /**
     * Modify buy
     * 
     * @param buy
     * @return
     */
    public Response modify(Buy buy);

    /**
     * Find list
     * 
     * @param buy
     * @return
     */
    public Map<String, Object> findList(Buy buy);

    public Map<String, Object> findById(Buy buy);

    public Map<String, Object> findByUserId(Buy buy);
    
    public int findCount(Buy buy);

    public Response ajaxFindList(Buy buy);

    public Map<String, ?> addPre(Long id);

    public Response ajaxFindByUserId(Buy buy);

    public Response refresh(String detailIds, Long id);

    public Response down(String detailIds, Long id);

    public Response on(String detailIds, Long id);

    public Map<String, Object> editPre(Long id, Long id2);

    public Response modify(Buy buy, List<String> result);

    public Map<String, Object> findAdminList(Buy buy);

    public Response pass(Long id);

    public Response reject(Long id, Integer type, String opinion);

    public Response passAll(String ids);

    public Response rejectAll(String ids, Integer type, String opinion);
}
