package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Sell;

@Repository
public interface SellDAO {

    public void save(Sell sell);

    public int update(Sell sell);

    public List<Sell> findSells(Sell sell);

    public Integer findSellCount(Sell sell);

    public List<Long> findAllProvincesBySell(Sell sell);

    public Sell getById(Long id);
}
