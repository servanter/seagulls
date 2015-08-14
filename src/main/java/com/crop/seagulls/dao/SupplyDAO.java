package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Supply;

@Repository
public interface SupplyDAO {

    public void save(Supply supply);

    public int update(Supply supply);

    public List<Supply> findSupplies(Supply supply);

    public Integer findSuppliesCount(Supply supply);

    public List<Long> findAllProvincesBySupply(Supply supply);

}
