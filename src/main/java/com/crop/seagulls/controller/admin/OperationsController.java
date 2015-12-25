package com.crop.seagulls.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Banner;
import com.crop.seagulls.entities.HotCategory;
import com.crop.seagulls.service.BannerService;
import com.crop.seagulls.service.HotCategoryService;
import com.crop.seagulls.util.UploadUtils;

@Controller
@RequestMapping("/admin/operations")
public class OperationsController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private HotCategoryService hotCategoryService;

    @RequestMapping("/indexBanner_n{page:\\d+}")
    public String indexBanner(@PathVariable("page")
    Integer page, Model model) {
        Banner banner = new Banner();
        banner.setPage(page);
        model.addAttribute("list", bannerService.findList(banner));
        return "admin/operations/index_banner_list";
    }

    @ResponseBody
    @RequestMapping("/indexBanner/ajaxFindById")
    public Response ajaxFindById(@RequestParam("id")
    Integer id) {
        Response response = bannerService.findById(id);
        return response;
    }

    @ResponseBody
    @RequestMapping("/indexBanner/modify")
    public Response modify(Banner banner, HttpServletRequest request) {
        UploadUtils.upload("images/edit/banner/", "images/edit/banner/", banner, request);
        return bannerService.modify(banner);
    }

    @ResponseBody
    @RequestMapping("/indexBanner/top")
    public Response indexBannerTop(@RequestParam("id")
    Long id) {
        return bannerService.top(id);
    }

    @ResponseBody
    @RequestMapping("/indexBanner/add")
    public Response add(Banner banner, HttpServletRequest request) {
        UploadUtils.upload("images/edit/banner/", "images/edit/banner/", banner, request);
        return bannerService.add(banner);
    }

    @ResponseBody
    @RequestMapping("/indexBanner/remove")
    public Response remove(@RequestParam("id")
    Long id) {
        return bannerService.remove(id);
    }
    
    @ResponseBody
    @RequestMapping("/indexBanner/refresh")
    public Response indexBannerRefresh() {
        return bannerService.refresh();
    }

    @RequestMapping("/hotCategoies_n{page:\\d+}")
    public String hotCategoies(@PathVariable("page")
    Integer page, Model model) {
        HotCategory hotCategory = new HotCategory();
        hotCategory.setPage(page);
        model.addAttribute("list", hotCategoryService.findList(hotCategory));
        return "admin/operations/hot_category_list";
    }

    @ResponseBody
    @RequestMapping("/hotCategoies/ajaxFindById")
    public Response hotCategoiesAjaxFindById(@RequestParam("id")
    Integer id) {
        Response response = hotCategoryService.findById(id);
        return response;
    }

    @ResponseBody
    @RequestMapping("/hotCategoies/modify")
    public Response hotCategoiesModify(HotCategory hotCategory, HttpServletRequest request) {
        return hotCategoryService.modify(hotCategory);
    }

    @ResponseBody
    @RequestMapping("/hotCategoies/top")
    public Response hotCategoiesTop(@RequestParam("id")
    Long id) {
        return hotCategoryService.top(id);
    }

    @ResponseBody
    @RequestMapping("/hotCategoies/add")
    public Response hotCategoiesAdd(HotCategory hotCategory, HttpServletRequest request) {
        return hotCategoryService.add(hotCategory);
    }

    @ResponseBody
    @RequestMapping("/hotCategoies/remove")
    public Response hotCategoiesRemove(@RequestParam("id")
    Long id) {
        return hotCategoryService.remove(id);
    }
    
    @ResponseBody
    @RequestMapping("/hotCategoies/refresh")
    public Response hotCategoiesRefresh() {
        return hotCategoryService.refresh();
    }

}
