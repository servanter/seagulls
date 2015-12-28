package com.crop.seagulls.dao;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.PersonRejection;

@Repository
public interface PersonRejectionDAO {

    int save(PersonRejection rejection);

}
