package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.SellRejection;

@Repository
public interface SellRejectionDAO {

    int save(SellRejection rejection);

    void batchSave(List<SellRejection> rejections);

    List<SellRejection> getList(SellRejection SellRejection);

    int getListCount(SellRejection SellRejection);
}
