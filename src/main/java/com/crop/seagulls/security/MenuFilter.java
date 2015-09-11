package com.crop.seagulls.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.crop.seagulls.cache.MenuCache;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.entities.admin.Menu;
import com.crop.seagulls.entities.admin.User;
import com.crop.seagulls.util.SecurityUtils;

public class MenuFilter implements Filter {

    private MenuCache menuCache;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        menuCache = (MenuCache) webApplicationContext.getBean("menuCache");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String contextPath = req.getContextPath();
        String fullURL = req.getRequestURI();
        String targetURL = "";
        if (fullURL.length() > 0) {
            targetURL = fullURL.substring(contextPath.length() + 1);
        }
        UserDetails userDetails = SecurityUtils.getLoginedPrincipal();
        if (userDetails != null) {
            User user = (User) userDetails;
            List<Menu> menus = new ArrayList<Menu>();
            Map<Long, List<Menu>> menuMap = user.getMenuMap();
            for (Entry<Long, List<Menu>> entry : menuMap.entrySet()) {
                menus.addAll(entry.getValue());
            }
            Menu target = null;
            for (Menu menu : menus) {
                if (targetURL.contains(menu.getUrl()) && StringUtils.isNotBlank(menu.getUrl())) {
                    target = menu;
                    break;
                }
            }
            if (target != null) {

                // find top menus
                Menu top = null;
                Menu menu = target;
                for (;;) {
                    if (menu.getParentId() == -1) {
                        top = menu;
                        break;
                    }
                    menu = menuCache.getById(menu.getParentId());
                }
                List<Menu> curMenus = getCurMenuMap(top, user);
                req.setAttribute(Constant.ADMIN_USER_TOP_MENU_LIST, curMenus);
                req.setAttribute(Constant.ADMIN_USER_VISIT_TOP_MENU, top);
                req.setAttribute(Constant.ADMIN_USER_VISIT_MENU, target);
            }

        }
        filter.doFilter(request, response);
    }

    private List<Menu> getCurMenuMap(Menu menu, User user) {
        List<Menu> curMenus = new ArrayList<Menu>();
        getCurMenus(menu, user.getMenuMap(), curMenus);
        return curMenus;
    }

    /**
     * Get current page menus and order menu.<br>
     * <code>
     * 1000, 1000
     * 2000, 2000
     * 20001000, 20001000
     * 20002000, 20002000
     * 3000, 3000
     * 30001000, 30001000
     * 30002000, 30002000
     * 300020001000, 300020001000
     * 300020002000, 300020002000
     * 30003000, 30003000
     * </code>
     * 
     * @param menu
     * @param allMenus
     * @param menus
     */
    public void getCurMenus(Menu menu, Map<Long, List<Menu>> allMenus, List<Menu> menus) {
        List<Menu> subMenus = allMenus.get(menu.getId());
        if (subMenus != null) {
            if (menus.size() == 0) {
                menus.addAll(subMenus);
            } else {
                int index = 0;
                for (Menu m : menus) {
                    if (m.getId() == menu.getId()) {
                        break;
                    }
                    index++;
                }
                for (int i = 0; i < subMenus.size(); i++) {
                    menus.add(i + index + 1, subMenus.get(i));
                }
            }

            for (Menu m : subMenus) {
                getCurMenus(m, allMenus, menus);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
