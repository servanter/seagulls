package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.SellPic;

@Repository
public interface SellPicDAO {

    public List<SellPic> getBySell(Long id);

    public void save(SellPic pic);

    public List<SellPic> getAll();

    public List<SellPic> getList(SellPic sellPic);

    public int update(SellPic sellPic);
}
