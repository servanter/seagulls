package com.crop.seagulls.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.entities.Message;
import com.crop.seagulls.service.DictAreaService;
import com.crop.seagulls.service.MessageService;

@Controller
@RequestMapping("/admin/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/list_n{page:\\d+}")
    public String list(@PathVariable("page") Integer page, Model model) {
        Message message = new Message();
        message.setPageSize(20);
        message.setPage(page);
        Paging<Message> messages = messageService.findByPage(message);
        model.addAttribute("result", messages);
        model.addAttribute("s", message);
        return "admin/message/list";
    }
}
