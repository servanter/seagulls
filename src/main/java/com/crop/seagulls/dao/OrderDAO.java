package com.crop.seagulls.dao;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Order;

@Repository
public interface OrderDAO {

    int save(Order order);

    int update(Order order);

}
