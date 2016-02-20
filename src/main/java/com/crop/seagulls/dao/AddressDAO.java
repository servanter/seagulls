package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Address;

@Repository
public interface AddressDAO {

    int save(Address address);

    List<Address> getByUserId(Long userId);

    Address getUserAddressOne(@Param("id")
    Long id, @Param("userId")
    Long userId);

}
