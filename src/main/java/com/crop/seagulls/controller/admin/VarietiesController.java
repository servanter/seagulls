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
import com.crop.seagulls.entities.Varieties;
import com.crop.seagulls.service.VarietiesService;

@Controller
@RequestMapping("/admin/varieties")
public class VarietiesController {

    @Autowired
    private VarietiesService varietiesService;

    @RequestMapping("/list_n{page:\\d+}")
    public String list(@PathVariable("page")
    Integer page, @RequestParam(value = "categoryId", defaultValue = "0", required = false)
    long categoryId, Model model) {
        Varieties varieties = new Varieties();
        varieties.setCategoryId(categoryId);
        varieties.setPageSize(20);
        varieties.setPage(page);
        Paging<Varieties> varietiesList = varietiesService.findList(varieties);
        model.addAttribute("result", varietiesList);
        model.addAttribute("s", varieties);
        return "admin/varieties/list";
    }

    @ResponseBody
    @RequestMapping("/ajaxFindById")
    public Response ajaxFindById(@RequestParam("id")
    Integer id) {
        Response response = varietiesService.findById(id);
        return response;
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Response modify(Varieties varieties, HttpServletRequest request) {
        return varietiesService.modify(varieties);
    }

    @ResponseBody
    @RequestMapping("/add")
    public Response add(Varieties varieties, HttpServletRequest request) {
        Long start = varieties.getCategoryId() * 1000;
        Long next = Long.parseLong(String.valueOf(Long.parseLong(String.valueOf(String.valueOf(varieties.getCategoryId()).charAt(0))) + 1) + String.valueOf(varieties.getCategoryId()).substring(1));
        Long end = (next) * 1000;
        return varietiesService.add(varieties, start, end);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public Response remove(@RequestParam("id")
    Long id) {
        return varietiesService.remove(id);
    }

    @ResponseBody
    @RequestMapping("/publish")
    public Response remove(@RequestParam("id")
    Long id, @RequestParam("publish")
    Integer publish) {
        Varieties varieties = new Varieties();
        varieties.setId(id);
        varieties.setIsValid(publish == 1);
        return varietiesService.publish(varieties);
    }
    
    @ResponseBody
    @RequestMapping("/refresh")
    public Response refresh() {
        return varietiesService.refresh();
    }

}
