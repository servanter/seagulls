package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.VarietiesDAO;
import com.crop.seagulls.entities.Varieties;
import com.crop.seagulls.service.VarietiesService;
import com.crop.seagulls.util.PinyinUtils;

@Service
public class VarietiesServiceImpl implements VarietiesService {

    @Autowired
    private VarietiesDAO varietiesDAO;

    @Override
    public List<Varieties> findAll() {
        return varietiesDAO.getAll();
    }

    @Override
    public Paging<Varieties> findList(Varieties varieties) {
        List<Varieties> list = varietiesDAO.getList(varieties);
        int total = varietiesDAO.getListCount(varieties);
        return new Paging<Varieties>(total, varieties.getPage(), varieties.getPageSize(), list);
    }

    @Override
    public Response findById(Integer id) {
        Response response = new Response(ReturnCode.SUCCESS);
        Varieties varieties = varietiesDAO.getById(id);
        response.setResult(varieties);
        return response;
    }

    @Override
    public Response modify(Varieties varieties) {
        varieties.setEnName(PinyinUtils.getPinYin(varieties.getZhName()));
        varieties.setFirstLetter(String.valueOf(varieties.getEnName().charAt(0)).toUpperCase());
        return varietiesDAO.update(varieties) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response remove(Long id) {
        return varietiesDAO.delete(id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response publish(Varieties varieties) {
        return varietiesDAO.update(varieties) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response add(Varieties varieties, Long start, Long end) {
        varieties.setEnName(PinyinUtils.getPinYin(varieties.getZhName()));
        varieties.setFirstLetter(String.valueOf(varieties.getEnName().charAt(0)).toUpperCase());
        Long generateId = varietiesDAO.getNextId(start, end);
        if (generateId == null) {
            generateId = start + 1;
        }

        varieties.setId(generateId);
        return varietiesDAO.save(varieties) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
