package com.crop.seagulls.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.service.DictAreaService;

@Controller
@RequestMapping("/admin/area")
public class AreaController {

    @Autowired
    private DictAreaService dictAreaService;

    @RequestMapping("/list_n{page:\\d+}")
    public String list(@PathVariable("page")
    Integer page, @RequestParam(value = "parentId", defaultValue = "0", required = false)
    long pId, Model model) {
        Area area = new Area();
        area.setParentId(pId);
        area.setPageSize(20);
        area.setPage(page);

        Paging<Area> areas = dictAreaService.findByPage(area);
        model.addAttribute("result", areas);
        model.addAttribute("s", area);
        return "admin/area/list";
    }
}
