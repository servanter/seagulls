package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Message;

@Repository
public interface MessageDAO {

    int getListCount(Message message);

    List<Message> getList(Message message);

    int save(Message message);

    List<Message> getListWithUser(Long userId);
    
    List<Message> getAll(Message message);

}
