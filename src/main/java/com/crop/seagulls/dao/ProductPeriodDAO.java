package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.ProductPeriod;

@Repository
public interface ProductPeriodDAO {

    public List<ProductPeriod> findAll();

}
