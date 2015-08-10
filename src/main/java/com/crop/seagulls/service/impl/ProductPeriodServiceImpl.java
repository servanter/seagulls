package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.ProductPeriodDAO;
import com.crop.seagulls.entities.ProductPeriod;
import com.crop.seagulls.service.ProductPeriodService;

@Service
public class ProductPeriodServiceImpl implements ProductPeriodService {

    @Autowired
    private ProductPeriodDAO productPeriodDAO;

    @Override
    public List<ProductPeriod> queryAll() {
        return productPeriodDAO.findAll();
    }

}
