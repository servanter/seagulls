package com.crop.seagulls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("111111111111");
        return "index";
    }
}
