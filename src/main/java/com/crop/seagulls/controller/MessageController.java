package com.crop.seagulls.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Message;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.MessageService;
import com.crop.seagulls.util.SessionUtils;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message/messageIndex", method = RequestMethod.GET)
    public String messageIndex(HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        List<Message> messages = messageService.messageIndex(user.getId());
        model.addAttribute("list", messages);
        return "message/message_index";
    }

    @RequestMapping(value = "/message/messageDetail/{toUserId:\\d+}", method = RequestMethod.GET)
    public String messageDetail(@PathVariable("toUserId")
    Long toUserId, HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        Message message = new Message();
        message.setUserId(user.getId());
        message.setToUserId(toUserId);
        model.mergeAttributes(messageService.findLastest(message));
        return "message/message_detail";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxAuth/message/leaveMessage", method = RequestMethod.POST)
    public Response leaveMessage(Message message, HttpSession session) {
        User user = SessionUtils.getCurUser(session);
        message.setUserId(user.getId());
        return messageService.leaveMessage(message);
    }

}
