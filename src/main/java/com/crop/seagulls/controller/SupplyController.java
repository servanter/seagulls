package com.crop.seagulls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Supply controller
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
@RequestMapping("/supply")
public class SupplyController {

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String enterPublish(Model model) {
        return "supply/publish";
    }
    
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(Model model) {
        
        
        return "supply/publish";
    }
}
