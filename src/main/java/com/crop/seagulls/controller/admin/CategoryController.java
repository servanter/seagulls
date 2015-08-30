package com.crop.seagulls.controller.admin;

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

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private DictCategoryService categoryService;

    @RequestMapping("/list_n{page:\\d+}")
    public String list(@PathVariable("page")
    Integer page, @RequestParam(value = "pId", defaultValue = "0" ,required = false)
    long pId, Model model) {
        Category category = new Category();
        category.setPId(pId);
        category.setPageSize(20);
        category.setPage(page);
        Paging<Category> categories = categoryService.findByCategory(category);
        model.addAttribute("result", categories);
        model.addAttribute("s",category);
        return "admin/category/list";
    }

    @ResponseBody
    @RequestMapping("/ajaxFindById")
    public Response ajaxFindById (@RequestParam("id") Integer id){
        Response response = categoryService.findById(id);
        return response;
    }
    
    
    @ResponseBody
    @RequestMapping("/modify")
    public Response modify(@RequestParam("id") Long id,@RequestParam("pId") Long pId,@RequestParam("zhName") String zhName) {
        Category category = new Category();
        category.setId(id);
        category.setPId(pId);
        category.setZhName(zhName);
        return categoryService.modify(category);
    }
 
    @ResponseBody
    @RequestMapping("/add")
    public Response add(Category category) {
        return categoryService.add(category);
    }
    
    @ResponseBody
    @RequestMapping("/remove")
    public Response remove(@RequestParam("id") Long id) {
        return categoryService.remove(id);
    }
    
}
