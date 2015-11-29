package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.BannerDAO;
import com.crop.seagulls.entities.Banner;
import com.crop.seagulls.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;

    @Override
    public List<Banner> findAll() {
        return bannerDAO.getAll();
    }

}
