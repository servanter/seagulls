package com.crop.seagulls.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Supply;
import com.crop.seagulls.service.SupplyService;

@Controller
public class CategoryController {


    @Autowired
    private SupplyService supplyService;



}
