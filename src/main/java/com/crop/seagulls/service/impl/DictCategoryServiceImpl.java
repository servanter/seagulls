package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.DictCategoryDAO;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.service.DictCategoryService;
import com.crop.seagulls.util.PinyinUtils;

@Service("dictCategoryService")
public class DictCategoryServiceImpl implements DictCategoryService {

    @Autowired
    private DictCategoryDAO dictCategoryDAO;

    @Override
    public List<Category> findAll() {
        return dictCategoryDAO.getAll();
    }

    @Override
    public List<Category> findByPId(Long id) {
        return dictCategoryDAO.getByPId(id);
    }

    @Override
    public Response modify(Category category) {
        category.setEnName(PinyinUtils.getPinYin(category.getZhName()));
        category.setFirstLetter(String.valueOf(category.getEnName().charAt(0)).toUpperCase());
        return dictCategoryDAO.update(category) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response add(Category category) {
        category.setEnName(PinyinUtils.getPinYin(category.getZhName()));
        category.setFirstLetter(String.valueOf(category.getEnName().charAt(0)).toUpperCase());
        return dictCategoryDAO.insert(category) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Paging<Category> findByCategory(Category category) {
        List<Category> list = dictCategoryDAO.getByCategory(category);
        int total = dictCategoryDAO.getByCategoryCount(category);
        return new Paging<Category>(total, category.getPage(), category.getPageSize(), list);
    }

    @Override
    public Response ajaxFindById(Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        Category category = dictCategoryDAO.getById(id);
        response.setResult(category);
        return response;
    }
    
    @Override
    public Category findById(Long id) {
        return dictCategoryDAO.getById(id);
    }

    @Override
    public Response remove(Long id) {
        return dictCategoryDAO.delete(id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Long generateId(Long start, Long end) {
        Long generateId = dictCategoryDAO.getNextId(start, end);
        if (generateId == null) {
            generateId = start + 1;
        }
        return generateId;
    }

    @Override
    public Response publish(Category category) {
        return dictCategoryDAO.update(category) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response findCategory() {
        Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
        List<Category> list = findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Map<String, Object>> all = new ArrayList<Map<String, Object>>();
            if (CollectionUtils.isNotEmpty(list)) {
                for (Category m : list) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", m.getId());
                    map.put("parentId", m.getParentId());
                    map.put("text", m.getZhName());
                    all.add(map);
                }
            }
            result.put("all", all);
        }
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        response.setResult(result);
        return response;
    }

}
