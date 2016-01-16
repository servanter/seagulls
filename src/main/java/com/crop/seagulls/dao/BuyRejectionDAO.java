package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.BuyRejection;

@Repository
public interface BuyRejectionDAO {

    int save(BuyRejection rejection);

    void batchSave(List<BuyRejection> rejections);

    List<BuyRejection> getList(BuyRejection buyRejection);

    int getListCount(BuyRejection buyRejection);
}
