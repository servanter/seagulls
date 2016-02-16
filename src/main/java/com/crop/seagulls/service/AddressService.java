package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Address;

public interface AddressService {

    public Response add(Address address);
    
    public List<Address> findUserAddress(Long userId);
}
