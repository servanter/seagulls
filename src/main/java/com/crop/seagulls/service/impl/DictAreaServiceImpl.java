package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.dao.DictAreaDAO;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.service.DictAreaService;

@Service
public class DictAreaServiceImpl implements DictAreaService {

    @Autowired
    private DictAreaDAO dictAreaDAO;

    @Override
    public List<Area> findList() {
        return dictAreaDAO.getList();
    }

    @Override
    public Boolean modify(Area area) {
        int affect = dictAreaDAO.update(area);
        return affect > 0 ? true : false;
    }

    @Override
    public Boolean remove(Long id) {
        int affect = dictAreaDAO.delete(id);
        return affect > 0 ? true : false;
    }

    @Override
    public Boolean save(Area area) {
        int affect = dictAreaDAO.insert(area);
        return affect > 0 ? true : false;
    }

    @Override
    public List<Area> findByPId(Long id) {
        return dictAreaDAO.getByPId(id);
    }

}
