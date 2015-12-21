package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Varieties;

@Repository
public interface VarietiesDAO {

    List<Varieties> getAll();

    /**
     * Update
     * 
     * @param varieties
     * @return
     */
    public int update(Varieties varieties);

    /**
     * Insert
     * 
     * @param varieties
     * @return
     */
    public int save(Varieties varieties);

    public List<Varieties> getList(Varieties varieties);

    public int getListCount(Varieties varieties);

    public Varieties getById(Integer id);

    public int delete(Long id);

    public Long getNextId(@Param("start")
    Long start, @Param("end")
    Long end);
}
