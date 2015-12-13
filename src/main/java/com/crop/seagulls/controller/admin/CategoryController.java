package com.crop.seagulls.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.service.DictCategoryService;
import com.crop.seagulls.util.UploadUtils;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private DictCategoryService categoryService;

    @RequestMapping("/list_n{page:\\d+}")
    public String list(@PathVariable("page")
    Integer page, @RequestParam(value = "pId", defaultValue = "0", required = false)
    long pId, Model model) {
        Category category = new Category();
        category.setPId(pId);
        category.setPageSize(20);
        category.setPage(page);
        Paging<Category> categories = categoryService.findByCategory(category);
        model.addAttribute("result", categories);
        model.addAttribute("s", category);
        return "admin/category/list";
    }

    @ResponseBody
    @RequestMapping("/ajaxFindById")
    public Response ajaxFindById(@RequestParam("id")
    Integer id) {
        Response response = categoryService.findById(id);
        return response;
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Response modify(Category category, HttpServletRequest request) {
        UploadUtils.upload("images/edit/category/", "images/edit/category/", category, "category_" + category.getId(), request);
        return categoryService.modify(category);
    }

    @ResponseBody
    @RequestMapping("/add")
    public Response add(Category category, HttpServletRequest request) {
        Long start = category.getPId() * 1000;
        Long next = Long.parseLong(String.valueOf(Long.parseLong(String.valueOf(String.valueOf(category.getPId()).charAt(0))) + 1) + String.valueOf(category.getPId()).substring(1));
        Long end = (next) * 1000;
        Long insertId = categoryService.generateId(start, end);
        category.setId(insertId);
        UploadUtils.upload("images/edit/category/", "images/edit/category/", category, "category_" + insertId, request);
        return categoryService.add(category);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public Response remove(@RequestParam("id")
    Long id) {
        return categoryService.remove(id);
    }
    
    @ResponseBody
    @RequestMapping("/publish")
    public Response remove(@RequestParam("id")
    Long id, @RequestParam("publish") Integer publish) {
        Category category = new Category();
        category.setId(id);
        category.setIsValid(publish == 1);
        return categoryService.publish(category);
    }

}
