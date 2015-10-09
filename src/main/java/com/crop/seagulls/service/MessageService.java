package com.crop.seagulls.service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.entities.Message;

public interface MessageService {

    public Paging<Message> findByPage(Message message);

}
