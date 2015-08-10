package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.SupplyPic;

@Repository
public interface SupplyPicDAO {

    public List<SupplyPic> findBySupplyId(Long id);

}
