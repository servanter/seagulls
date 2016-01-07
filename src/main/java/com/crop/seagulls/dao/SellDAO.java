package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Sell;

@Repository
public interface SellDAO {

    public void save(Sell sell);

    public int update(Sell sell);

    public List<Sell> getList(Sell sell);

    public Integer getListCount(Sell sell);

    public List<Long> findAllProvincesBySell(Sell sell);

    public Sell getById(Long id);

    public List<Long> getTopCategories(@Param("categoryAlias") String string);
}
