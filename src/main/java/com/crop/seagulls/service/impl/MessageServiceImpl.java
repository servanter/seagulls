package com.crop.seagulls.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.MessageDAO;
import com.crop.seagulls.entities.Message;
import com.crop.seagulls.service.MessageService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private TemplateService templateService;
    
    @Override
    public Map<String, Object> findLastest(Message message) {
        Map<String, Object> map = new HashMap<String, Object>();
//        int total = messageDAO.getListCount(message);

        // display lastest 5 messages
//        int sinceCount = 0;
//        if (total - 5 >= 5) {
//            sinceCount = total - 5;
//        } else if (total > 5 && total - 5 < 5) {
//            sinceCount = total - (total - 5);
//        }
//        message.setSinceCount(sinceCount);
//        message.setPageSize(5);
//        List<Message> list = messageDAO.getList(message);
        List<Message> list = messageDAO.getAll(message);
        for(Message m : list) {
            DateType dateType = DateUtils.getTimeDesc(m.getCreateTime());
            m.setPageTimeAlias(templateService.getMessage("page.time.alias." + dateType.getType(), String.valueOf(dateType.getMoreThan())));
        }
        map.put("list", list);
        map.put("toUser", userService.findUserById(message.getToUserId()));
        map.put("user", userService.findUserById(message.getUserId()));
        return map;
    }

    @Override
    public Response leaveMessage(Message message) {
        Response response = messageDAO.save(message) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        response.setResult(message.getMessage());
        return response;
    }

    @Override
    public List<Message> messageIndex(Long userId) {
        List<Message> list = messageDAO.getListWithUser(userId);
        // time alias
        for(Message message : list) {
            DateType dateType = DateUtils.getTimeDesc(message.getCreateTime());
            message.setPageTimeAlias(templateService.getMessage("page.time.alias." + dateType.getType(), String.valueOf(dateType.getMoreThan())));
        }
        return list;
    }

}
