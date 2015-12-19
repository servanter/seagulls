package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.HotCategory;

@Repository
public interface HotCategoryDAO {

    List<HotCategory> getAll();

    int save(HotCategory hotCategory);

    HotCategory getById(Integer id);

    int update(HotCategory hotCategory);

    int getMaxSeq();

    List<HotCategory> getList(HotCategory hotCategory);

    int getListCount(HotCategory hotCategory);
}
