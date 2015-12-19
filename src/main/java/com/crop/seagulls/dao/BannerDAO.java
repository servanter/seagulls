package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Banner;

@Repository
public interface BannerDAO {

    List<Banner> getAll();

    int save(Banner banner);

    int update(Banner banner);

    Banner getById(Integer id);

    int getMaxSeq();

    List<Banner> getList(Banner banner);

    int getListCount(Banner banner);

}
