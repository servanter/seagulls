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
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.service.MenuService;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/findMenus")
    public Response findMenus(@RequestParam("role_ids")
    String roleIds) {
        return menuService.loadMenusAndWithCurMenus(roleIds);
    }

    @RequestMapping("/list_n{page:\\d+}")
    public String findList(Menu menu, @PathVariable("page")
    Integer page, Model model) {
        menu.setPageSize(20);
        menu.setPage(page);
        Paging<Menu> result = menuService.findList(menu);
        model.addAttribute("result", result);
        return "admin/menu/list";
    }
    
    @ResponseBody
    @RequestMapping("/ajaxFindById")
    public Response ajaxFindById (@RequestParam("id") Long id) {
        return menuService.findById(id);
    }
    
    
    @ResponseBody
    @RequestMapping("/modify")
    public Response modify(Menu menu) {
        return menuService.modify(menu);
    }
 
    @ResponseBody
    @RequestMapping("/add")
    public Response add(Menu menu) {
        return menuService.add(menu);
    }
    
    @ResponseBody
    @RequestMapping("/remove")
    public Response remove(@RequestParam("id") Long id) {
        return menuService.remove(id);
    }
    
}
