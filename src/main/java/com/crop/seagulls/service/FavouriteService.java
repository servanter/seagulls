package com.crop.seagulls.service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Favourite;

public interface FavouriteService {

    Response favourite(Favourite favourite);

    void findList(Favourite favourite);

    Boolean hasFavourite(Favourite favourite);

    Response unFavourite(Favourite favourite);
}
