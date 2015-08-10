package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Supply;

@Repository
public interface SupplyDAO {

    public void save(Supply supply);

    public int update(Supply supply);

    public List<Supply> findBuies(Supply supply);

    public Integer findBuiesCount(Supply supply);

}
