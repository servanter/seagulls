package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Buy;

@Repository
public interface BuyDAO {

    public void save(Buy buy);

    public int update(Buy buy);

    public List<Buy> getList(Buy buy);

    public Integer getListCount(Buy buy);
    
    public List<Long> getAllProvinces(Buy buy);

    public Buy getById(Long id);
}
