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
        Response response = addressDAO.save(address) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        response.setResult(address.getId());
        return response;
    }

    @Override
    public List<Address> findUserAddress(Long userId) {
        return addressDAO.getByUserId(userId);
    }

    @Override
    public Address findUserAddress(Long id, Long userId) {
        return addressDAO.getUserAddressOne(id, userId);
    }

}
