package com.crop.seagulls.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.Company;
import com.crop.seagulls.service.CompanyService;

@Component
public class CompanyCache {

    public static Map<Long, Company> ALL_COMPANY = new HashMap<Long, Company>();

    @Autowired
    private CompanyService companyService;

    @PostConstruct
    public void init() {
        List<Company> companies = companyService.findAll();
        Map<Long, Company> map = new HashMap<Long, Company>();
        if (CollectionUtils.isNotEmpty(companies)) {
            for (Company company : companies) {
                map.put(company.getId(), company);
            }
        }
        ALL_COMPANY = map;
    }

    public Company getById(Long id) {
        return ALL_COMPANY.get(id);
    }
}
