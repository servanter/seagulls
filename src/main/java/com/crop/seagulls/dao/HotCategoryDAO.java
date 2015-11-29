package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface HotCategoryDAO {

    List<Long> getAll();

}
