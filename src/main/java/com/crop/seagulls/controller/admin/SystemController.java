package com.crop.seagulls.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminSystemController")
@RequestMapping("/admin/system")
public class SystemController {

    @RequestMapping("/home")
    public String home() {
        return "admin/system/system_home";
    }

}
