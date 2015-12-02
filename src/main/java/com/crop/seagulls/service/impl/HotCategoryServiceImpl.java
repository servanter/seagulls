package com.crop.seagulls.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.dao.HotCategoryDAO;
import com.crop.seagulls.entities.HotCategory;
import com.crop.seagulls.service.HotCategoryService;
import com.crop.seagulls.util.SecurityUtils;

@Service
public class HotCategoryServiceImpl implements HotCategoryService {

    @Autowired
    private HotCategoryDAO hotCategoryDAO;

    @Autowired
    private CategoryCache categoryCache;

    @Override
    public List<HotCategory> findAll() {
        List<HotCategory> list = hotCategoryDAO.getAll();
        if (CollectionUtils.isNotEmpty(list)) {
            for (HotCategory category : list) {
                if (categoryCache.getById(category.getCategoryId()) != null) {
                    category.setCategoryName(categoryCache.getById(category.getCategoryId()).getZhName());
                }
            }
        }
        return list;
    }

    @Override
    public Response add(HotCategory hotCategory) {
        hotCategory.setOperatorId(SecurityUtils.getLoginedUserId());
        hotCategory.setIsValid(true);
        return hotCategoryDAO.save(hotCategory) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response findById(Integer id) {
        Response response = new Response(ReturnCode.SUCCESS);
        HotCategory hotCategory = hotCategoryDAO.getById(id);
        response.setResult(hotCategory);
        return response;
    }

    @Override
    public Response remove(Long id) {
        HotCategory hotCategory = new HotCategory();
        hotCategory.setId(id);
        hotCategory.setIsValid(false);
        hotCategory.setOperatorId(SecurityUtils.getLoginedUserId());
        return hotCategoryDAO.update(hotCategory) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response modify(HotCategory hotCategory) {
        hotCategory.setOperatorId(SecurityUtils.getLoginedUserId());
        return hotCategoryDAO.update(hotCategory) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }
}
