package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Favourite;

@Repository
public interface FavouriteDAO {

    int save(Favourite favourite);

    List<Favourite> getList(Favourite favourite);

    int getListCount(Favourite favourite);

    int update(Favourite favourite);

}
