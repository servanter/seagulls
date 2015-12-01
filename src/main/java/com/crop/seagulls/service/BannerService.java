package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Banner;

public interface BannerService {

    public List<Banner> findAll();

    public Response findById(Integer id);

    public Response modify(Banner banner);

    public Response add(Banner banner);

    public Response remove(Long id);

}
