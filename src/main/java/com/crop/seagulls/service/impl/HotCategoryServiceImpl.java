package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.HotCategoryDAO;
import com.crop.seagulls.service.HotCategoryService;

@Service
public class HotCategoryServiceImpl implements HotCategoryService {

    @Autowired
    private HotCategoryDAO hotCategoryDAO;

    @Override
    public List<Long> findAll() {
        return hotCategoryDAO.getAll();
    }

}
