package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.FavouriteDAO;
import com.crop.seagulls.entities.Favourite;
import com.crop.seagulls.service.FavouriteService;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavouriteDAO favouriteDAO;

    @Override
    public Response favourite(Favourite favourite) {
        Response response = new Response(ReturnCode.SUCCESS);
        if (favourite.getUserId() <= 0) {
            response.setReturnCode(ReturnCode.USER_NOT_LOGINED);
        }
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            int total = favouriteDAO.getListCount(favourite);
            if (total > 0) {
                response.setReturnCode(ReturnCode.SUCCESS);
            } else {
                response.setReturnCode(favouriteDAO.save(favourite) > 0 ? ReturnCode.SUCCESS : ReturnCode.ERROR);
            }
        }
        return response;
    }

    @Override
    public Response unFavourite(Favourite favourite) {
        return favouriteDAO.update(favourite) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public void findList(Favourite favourite) {
        List<Favourite> favourities = favouriteDAO.getList(favourite);
        int total = favouriteDAO.getListCount(favourite);

    }

    @Override
    public Boolean hasFavourite(Favourite favourite) {
        return favouriteDAO.getListCount(favourite) > 0;
    }
}
