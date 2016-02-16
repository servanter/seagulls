package com.crop.seagulls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.AddressDAO;
import com.crop.seagulls.entities.Address;
import com.crop.seagulls.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Override
    public Response add(Address address) {
        return addressDAO.save(address) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public List<Address> findUserAddress(Long userId) {
        return addressDAO.getByUserId(userId);
    }

}
