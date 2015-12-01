package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.HotCategory;

public interface HotCategoryService {

    List<HotCategory> findAll();

    Response findById(Integer id);

    Response remove(Long id);

    Response add(HotCategory hotCategory);

    Response modify(HotCategory hotCategory);

}
