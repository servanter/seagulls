package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.DictCategoryDAO;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.service.DictCategoryService;

@Service("dictCategoryService")
public class DictCategoryServiceImpl implements DictCategoryService {

    @Autowired
    private DictCategoryDAO dictCategoryDAO;

    @Override
    public List<Category> findList() {
        return dictCategoryDAO.getList();
    }

    @Override
    public List<Category> findByPId(Long id) {
        return dictCategoryDAO.getByPId(id);
    }

    @Override
    public Boolean modify(Category category) {
        return dictCategoryDAO.update(category) > 0 ? true : false;
    }

    @Override
    public Boolean save(Category category) {
        return dictCategoryDAO.insert(category) > 0 ? true : false;
    }

}
