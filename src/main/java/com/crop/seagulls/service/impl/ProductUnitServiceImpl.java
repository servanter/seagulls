package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.ProductUnitDAO;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.service.ProductUnitService;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    private ProductUnitDAO productUnitDAO;
    
    @Override
    public List<ProductUnit> queryProductUnits() {
        return productUnitDAO.findAll();
    }

}
