package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.VarietiesDAO;
import com.crop.seagulls.entities.Varieties;
import com.crop.seagulls.service.VarietiesService;

@Service
public class VarietiesServiceImpl implements VarietiesService {

    @Autowired
    private VarietiesDAO varietiesDAO;
    
    @Override
    public List<Varieties> findAll() {
        return varietiesDAO.getAll();
    }

}
