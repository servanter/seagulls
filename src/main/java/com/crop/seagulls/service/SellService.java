package com.crop.seagulls.service;

import java.util.List;
import java.util.Map;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Sell;

/**
 * Supply Service
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public interface SellService {

    public Map<String, Object> addPre(Long userId);
    /**
     * Add supply
     * 
     * @param sell
     * @param webImagesPath
     * @return
     */
    public Response add(Sell sell, List<String> webImagesPath);

    /**
     * Query suppies
     * 
     * @param supply
     *            search model
     * @return
     */
    public Map<String, Object> findList(Sell sell);
    
    /**
     * Query suppies
     * 
     * @param supply
     *            search model
     * @return
     */
    public Response ajaxFindList(Sell sell);
    
    /**
     * Modify supply
     * 
     * @param supply
     * @return
     */
    public Response modify(Sell sell);
    
    
    public Map<String, Object> findById(Sell sell);
    public Sell findBaseInfoById(Long sellId);

    public Map<String, Object> findByUserId(Sell sell);

    public Response ajaxFindByUserId(Sell sell);
    
    public int findCount(Sell sell);

    public List<Category> findHotCategories();
    public Response refresh(String detailIds, Long id);
    public Response down(String detailIds, Long id);
    public Response on(String detailIds, Long id);
    public Map<String, Object> editPre(Long id, Long createUserId);
    public Response modify(Sell sell, List<String> result);
    public Map<String, Object> findAdminList(Sell sell);
    public Response pass(Long id);
    public Response reject(Long id, Integer type, String opinion);
    public Response passAll(String ids);
    public Response rejectAll(String ids, Integer type, String opinion);
    public Map<String, Object> findAdminById(Sell sell);
    public Map<String, Object> purchasePre(Long sellId, Long userId);
    
}
