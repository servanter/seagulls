package com.crop.seagulls.service;

import java.util.List;
import java.util.Map;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Message;

public interface MessageService {

    List<Message> messageIndex(Long id);

    Map<String, Object> findLastest(Message message);

    Response leaveMessage(Message message);

}
