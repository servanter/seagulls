package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.PersonRejection;

@Repository
public interface PersonRejectionDAO {

    int save(PersonRejection rejection);

    void batchSave(List<PersonRejection> rejections);

}
