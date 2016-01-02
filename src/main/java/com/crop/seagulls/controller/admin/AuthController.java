package com.crop.seagulls.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.CompanyRejectEnum;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.PersonRejectEnum;
import com.crop.seagulls.bean.Response;
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
    
    @RequestMapping("/company/auditList_n{page:\\d+}")
    public String companyAuditList(@PathVariable("page")
    Integer page, Model model) {
        Company company = new Company();
        company.setPageSize(20);
        company.setPage(page);
        company.setStatus(CommonStatus.AUDITING.getCode());
        Paging<Company> companies = companyService.findList(company);
        model.addAttribute("rejects", CompanyRejectEnum.values());
        model.addAttribute("list", companies);
        model.addAttribute("s", company);
        return "admin/auth/company_auditing_list";
    }
    
    @RequestMapping("/company/rejectList_n{page:\\d+}")
    public String companyRejectList(@PathVariable("page")
    Integer page, Model model) {
        Company company = new Company();
        company.setPageSize(20);
        company.setPage(page);
        company.setStatus(CommonStatus.REJECT.getCode());
        Paging<Company> companies = companyService.findList(company);
        model.addAttribute("rejects", PersonRejectEnum.values());
        model.addAttribute("list", companies);
        model.addAttribute("s", company);
        return "admin/auth/company_reject_list";
    }
    
    @RequestMapping("/company/passList_n{page:\\d+}")
    public String companyPassList(@PathVariable("page")
    Integer page, Model model) {
        Company company = new Company();
        company.setPageSize(20);
        company.setPage(page);
        company.setStatus(CommonStatus.PASS.getCode());
        Paging<Company> companies = companyService.findList(company);
        model.addAttribute("rejects", PersonRejectEnum.values());
        model.addAttribute("list", companies);
        model.addAttribute("s", company);
        return "admin/auth/company_pass_list";
    }
    
    @ResponseBody
    @RequestMapping("/company/pass")
    public Response companyPass(@RequestParam("id") Long id) {
        return companyService.pass(id);
    }
    
    @ResponseBody
    @RequestMapping("/company/reject")
    public Response companyReject(@RequestParam("type") Integer type, @RequestParam("opinion") String opinion, @RequestParam("id") Long id) {
        return companyService.reject(id, type, opinion);
    }
    
    @ResponseBody
    @RequestMapping("/company/passAll")
    public Response companyPassAll(@RequestParam("ids") String ids) {
        return companyService.passAll(ids);
    }
    
    @ResponseBody
    @RequestMapping("/company/rejectAll")
    public Response companyRejectAll(@RequestParam("ids") String ids, @RequestParam("type") Integer type, @RequestParam("opinion") String opinion) {
        return companyService.rejectAll(ids, type, opinion);
    }
    
    @RequestMapping("/person/auditList_n{page:\\d+}")
    public String personAuditList(@PathVariable("page")
    Integer page, Model model) {
        UserAuth auth = new UserAuth();
        auth.setPageSize(20);
        auth.setPage(page);
        auth.setStatus(CommonStatus.AUDITING.getCode());
        Paging<UserAuth> auths = userAuthService.findList(auth);
        model.addAttribute("rejects", PersonRejectEnum.values());
        model.addAttribute("list", auths);
        model.addAttribute("s", auth);
        return "admin/auth/person_auditing_list";
    }
    
    @RequestMapping("/person/rejectList_n{page:\\d+}")
    public String personUnAuditList(@PathVariable("page")
    Integer page, Model model) {
        UserAuth auth = new UserAuth();
        auth.setPageSize(20);
        auth.setPage(page);
        auth.setStatus(CommonStatus.REJECT.getCode());
        Paging<UserAuth> auths = userAuthService.findList(auth);
        model.addAttribute("list", auths);
        model.addAttribute("s", auth);
        return "admin/auth/person_reject_list";
    }
    
    @RequestMapping("/person/passList_n{page:\\d+}")
    public String personPassList(@PathVariable("page")
    Integer page, Model model) {
        UserAuth auth = new UserAuth();
        auth.setPageSize(20);
        auth.setPage(page);
        auth.setStatus(CommonStatus.PASS.getCode());
        Paging<UserAuth> auths = userAuthService.findList(auth);
        model.addAttribute("list", auths);
        model.addAttribute("s", auth);
        return "admin/auth/person_pass_list";
    }
    
    @ResponseBody
    @RequestMapping("/person/pass")
    public Response personPass(@RequestParam("id") Long id) {
        return userAuthService.pass(id);
    }
    
    @ResponseBody
    @RequestMapping("/person/reject")
    public Response personReject(@RequestParam("type") Integer type, @RequestParam("opinion") String opinion, @RequestParam("id") Long id) {
        return userAuthService.reject(id, type, opinion);
    }
    
    @ResponseBody
    @RequestMapping("/person/passAll")
    public Response personPassAll(@RequestParam("ids") String ids) {
        return userAuthService.passAll(ids);
    }
    
    @ResponseBody
    @RequestMapping("/person/rejectAll")
    public Response personRejectAll(@RequestParam("ids") String ids, @RequestParam("type") Integer type, @RequestParam("opinion") String opinion) {
        return userAuthService.rejectAll(ids, type, opinion);
    }

}
