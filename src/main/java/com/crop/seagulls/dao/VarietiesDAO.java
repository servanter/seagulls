package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Varieties;

@Repository
public interface VarietiesDAO {

    List<Varieties> getAll();

}
