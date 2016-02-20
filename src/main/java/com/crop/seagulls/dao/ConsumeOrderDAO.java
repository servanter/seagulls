package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.ConsumeOrder;

@Repository
public interface ConsumeOrderDAO {

    int save(ConsumeOrder order);

    int update(ConsumeOrder order);

    ConsumeOrder getByOrderIdAndUserId(@Param("id") String id, @Param("userId") Long userId);

    List<ConsumeOrder> getList(ConsumeOrder order);
    
    int getListCount(ConsumeOrder order);
}
