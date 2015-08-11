package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.DictProductPeriodDAO;
import com.crop.seagulls.entities.ProductPeriod;
import com.crop.seagulls.service.DictProductPeriodService;

@Service
public class DictProductPeriodServiceImpl implements DictProductPeriodService {

    @Autowired
    private DictProductPeriodDAO dictProductPeriodDAO;

    @Override
    public List<ProductPeriod> findPeriods() {
        return dictProductPeriodDAO.getAll();
    }

}
