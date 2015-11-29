package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Banner;

@Repository
public interface BannerDAO {

    List<Banner> getAll();

}
