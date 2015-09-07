package com.crop.seagulls.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/audit")
public class AuditController {

    @RequestMapping("/home")
    public String home() {
        return "admin/audit/audit_home";
    }

}
