package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.DictProductUnitDAO;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.service.DictProductUnitService;

@Service
public class DictProductUnitServiceImpl implements DictProductUnitService {

    @Autowired
    private DictProductUnitDAO dictProductUnitDAO;

    @Override
    public List<ProductUnit> findProductUnits() {
        return dictProductUnitDAO.getAll();
    }

}
