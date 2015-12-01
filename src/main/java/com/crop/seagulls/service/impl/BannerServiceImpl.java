package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.BannerDAO;
import com.crop.seagulls.entities.Banner;
import com.crop.seagulls.service.BannerService;
import com.crop.seagulls.util.SecurityUtils;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;

    @Override
    public List<Banner> findAll() {
        return bannerDAO.getAll();
    }

    @Override
    public Response add(Banner banner) {
        banner.setOperatorId(SecurityUtils.getLoginedUserId());
        banner.setIsValid(true);
        return bannerDAO.save(banner) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response findById(Integer id) {
        Response response = new Response(ReturnCode.SUCCESS);
        Banner banner = bannerDAO.getById(id);
        response.setResult(banner);
        return response;
    }

    @Override
    public Response modify(Banner banner) {
        banner.setOperatorId(SecurityUtils.getLoginedUserId());
        return bannerDAO.update(banner) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response remove(Long id) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setIsValid(false);
        banner.setOperatorId(SecurityUtils.getLoginedUserId());
        return bannerDAO.update(banner) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

}
