package com.crop.seagulls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crop.seagulls.service.SeriesService;

/**
 * 首页
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
@Controller
public class IndexController {

    @Autowired
    private SeriesService seriesService;

    @RequestMapping("/")
    public String index(Model model) {
        model.mergeAttributes(seriesService.index());
        return "index";
    }
}
