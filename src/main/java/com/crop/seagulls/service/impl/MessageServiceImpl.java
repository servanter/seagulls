package com.crop.seagulls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.dao.MessageDAO;
import com.crop.seagulls.entities.Message;
import com.crop.seagulls.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public Paging<Message> findByPage(Message message) {
        return messageDAO.getList(message);
    }

}
