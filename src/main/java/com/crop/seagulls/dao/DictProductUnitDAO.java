package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.ProductUnit;

@Repository
public interface DictProductUnitDAO {

    public List<ProductUnit> getAll();

}
