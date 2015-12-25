package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.HotCategory;

public interface HotCategoryService {

    public Paging<HotCategory> findList(HotCategory hotCategory);

    List<HotCategory> findAll();

    Response findById(Integer id);

    Response remove(Long id);

    Response add(HotCategory hotCategory);

    Response modify(HotCategory hotCategory);

    Response top(Long id);

    public Response refresh();

}
