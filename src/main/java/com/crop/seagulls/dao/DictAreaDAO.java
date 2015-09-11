package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Area;

@Repository
public interface DictAreaDAO {

    public List<Area> getList();

    public int update(Area area);

    public int delete(Long id);

    public int insert(Area area);

    public List<Area> getByPId(Long id);

    public List<Area> getByArea(Area area);

    public int getByAreaCount(Area area);

}
