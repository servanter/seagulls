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
import com.crop.seagulls.entities.admin.Role;
import com.crop.seagulls.service.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list_n{page:\\d+}")
    public String list(@PathVariable("page")
    Integer page, @RequestParam(value = "roleCode", required = false)
    String roleCode, @RequestParam(value = "roleName", required = false)
    String roleName, Model model) {
        Role role = new Role();
        role.setPageSize(20);
        role.setPage(page);
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        Paging<Role> roles = roleService.findByRole(role);
        model.addAttribute("result", roles);
        model.addAttribute("s", role);
        return "admin/role/list";
    }

    @ResponseBody
    @RequestMapping("/ajaxFindById")
    public Response ajaxFindById(@RequestParam("id")
    Long id) {
        Response response = roleService.findById(id);
        return response;
    }

    @ResponseBody
    @RequestMapping("/modify")
    public Response modify(@RequestParam("id")
    Long id, @RequestParam("roleCode")
    String roleCode, @RequestParam("roleName")
    String roleName) {
        Role role = new Role();
        role.setId(id);
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        return roleService.modify(role);
    }

    @ResponseBody
    @RequestMapping("/add")
    public Response add(Role role) {
        return roleService.add(role);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public Response remove(@RequestParam("id")
    Long id) {
        return roleService.remove(id);
    }

}
