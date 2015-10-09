package com.crop.seagulls.dao;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.entities.Message;

@Repository
public interface MessageDAO {

    public Paging<Message> getList(Message message);

}
