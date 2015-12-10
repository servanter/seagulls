package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.BuyPic;

@Repository
public interface BuyPicDAO {

    public List<BuyPic> getByBuy(Long id);

    public void save(BuyPic pic);

    public List<BuyPic> getAll();

    public List<BuyPic> getList(BuyPic pic);
}
