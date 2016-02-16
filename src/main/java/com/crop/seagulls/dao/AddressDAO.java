package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Address;

@Repository
public interface AddressDAO {

    int save(Address address);

    List<Address> getByUserId(Long userId);

}
