package com.crop.seagulls.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.CompanyService;
import com.crop.seagulls.service.UserAuthService;

@Controller
@RequestMapping("/admin/auth")
public class AuthController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping("/company/list_n{page:\\d+}")
    public String companyList(@PathVariable("page")
    Integer page, Model model) {
        Company company = new Company();
        company.setPageSize(20);
        company.setPage(page);
        Paging<Company> companies = companyService.findList(company);
        model.addAttribute("result", companies);
        model.addAttribute("s", company);
        return "admin/auth/company_list";
    }

    @RequestMapping("/person/list_n{page:\\d+}")
    public String personList(@PathVariable("page")
    Integer page, Model model) {
        UserAuth auth = new UserAuth();
        auth.setPageSize(20);
        auth.setPage(page);
        Paging<UserAuth> auths = userAuthService.findList(auth);
        model.addAttribute("list", auths);
        model.addAttribute("s", auth);
        return "admin/auth/person_list";
    }

}
