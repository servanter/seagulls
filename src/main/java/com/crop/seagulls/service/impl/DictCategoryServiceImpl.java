package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
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

    @Override
    public Paging<Category> findByCategory(Category category) {
        List<Category> list = dictCategoryDAO.getByCategory(category);
        int total = dictCategoryDAO.getByCategoryCount(category);
        return new Paging<Category>(total, category.getPage(), category.getPageSize(), list);
    }

    @Override
    public Response findById(Integer id) {
        Response response = new Response(ReturnCode.SUCCESS);
        Category category = dictCategoryDAO.getById(id);
        response.setResult(category);
        return response;
    }

}
