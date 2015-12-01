package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.HotCategoryDAO;
import com.crop.seagulls.entities.HotCategory;
import com.crop.seagulls.service.HotCategoryService;
import com.crop.seagulls.util.SecurityUtils;

@Service
public class HotCategoryServiceImpl implements HotCategoryService {

    @Autowired
    private HotCategoryDAO hotCategoryDAO;

    @Override
    public List<HotCategory> findAll() {
        return hotCategoryDAO.getAll();
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
