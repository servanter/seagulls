package com.crop.seagulls.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.service.MenuService;
import com.crop.seagulls.util.Logger;

@Component
public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Logger logger = Logger.getLogger(SecurityMetadataSource.class);

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        logger.debug("requestUrl is " + requestUrl);
        if (resourceMap == null) {
            loadResourceDefine();
        }
        if (StringUtils.isNotBlank(requestUrl)) {
            requestUrl = requestUrl.substring(1, requestUrl.length());
        }
        return resourceMap.get(requestUrl);
    }

    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Menu> menus = menuService.findAll();
            for (Menu menu : menus) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                String codes = menu.getRoleCodes();
                for (String code : codes.split(",")) {
                    ConfigAttribute configAttribute = new SecurityConfig(code);
                    configAttributes.add(configAttribute);
                }
                resourceMap.put(menu.getUrl(), configAttributes);
            }
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
