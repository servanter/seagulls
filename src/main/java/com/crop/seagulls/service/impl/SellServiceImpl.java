package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.FavouriteType;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.bean.SellBuy;
import com.crop.seagulls.cache.AreaCache;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.CompanyCache;
import com.crop.seagulls.cache.DetailPicCache;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.cache.VarietiesCache;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.dao.SellDAO;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.Favourite;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.entities.Sell;
import com.crop.seagulls.entities.SellPic;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.CompanyService;
import com.crop.seagulls.service.FavouriteService;
import com.crop.seagulls.service.SellPicService;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserAuthService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.NumberUtils;

@Service
public class SellServiceImpl implements SellService {

    private static Logger logger = Logger.getLogger(SellServiceImpl.class);

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private ProductRelationCache productRelationCache;

    @Autowired
    private SellDAO sellDAO;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private SellPicService sellPicService;

    @Autowired
    private AreaCache areaCache;

    @Autowired
    private VarietiesCache varietiesCache;

    @Autowired
    private DetailPicCache detailPicCache;

    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyCache companyCache;

    @Override
    public Response add(Sell sell, List<String> webImagesPath) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        sell.setCreateTime(new Date());
        sell.setUnitId(productRelationCache.getDefaultUnit());
        packageCategory(sell, sell.getSearchCategoryId());
        sellDAO.save(sell);
        if (sell.getId() == null || sell.getId() <= 0L) {
            response.setReturnCode(ReturnCode.ERROR);
        } else {
            if (CollectionUtils.isNotEmpty(webImagesPath)) {
                for (String picUrl : webImagesPath) {
                    SellPic pic = new SellPic();
                    pic.setSellId(sell.getId());
                    pic.setCreateUserId(sell.getCreateUserId());
                    pic.setCreateTime(new Date());
                    pic.setImgUrl(picUrl);
                    pic.setOperatorId(sell.getCreateUserId());
                    sellPicService.add(pic);
                }
            }
            detailPicCache.refresh(SellBuy.SELL, sell.getId());
            response.setResult(sell.getId());
        }
        return response;
    }

    @Override
    public Response modify(Sell sell) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        int affect = sellDAO.update(sell);
        if (affect <= 0) {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

    @Override
    public Map<String, Object> findList(Sell sell) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(sell, sell.getSearchCategoryId());
        sell.setIsValid(true);
        sell.setIsPublish(true);
        List<Sell> list = sellDAO.findSells(sell);
        Integer totalCount = sellDAO.findSellCount(sell);
        Paging<Sell> result = new Paging<Sell>(totalCount, sell.getPage(), sell.getPageSize(), list);
        packageModel(list);

        // fetch and display sub categories and
        if (sell.getSearchCategoryId() != null) {
            map.put("subCategories", categoryCache.getByPid(sell.getSearchCategoryId()));
        }
        map.put("list", result);
        map.put("topCategories", categoryCache.getByPid(Constant.CATEGORY_TOP_PID));
        return map;
    }

    @Override
    public Response ajaxFindList(Sell sell) {
        Response response = new Response(ReturnCode.SUCCESS);
        if (ObjectUtils.equals(sell.getPage(), null)) {
            sell.setPage(1);
        }
        Map<String, Object> data = findList(sell);
        if (data.containsKey("list") && CollectionUtils.isEmpty(((Paging<Sell>) data.get("list")).getResult())) {
            response.setReturnCode(ReturnCode.NO_MORE_PAGE);
        } else {
            int totalPage = ((Paging<Sell>) data.get("list")).getTotalPage();
            data.put("nextPage", sell.getPage() + 1);
        }
        response.setResult(data);
        return response;
    }

    private void packageModel(List<Sell> sells) {
        if (CollectionUtils.isNotEmpty(sells)) {
            for (Sell sell : sells) {

                // sell unit
                ProductUnit unit = productRelationCache.getUnitById(sell.getUnitId());
                sell.setPageUnit(unit);

                // find category
                // if (NumberUtils.isValidateNumber(sell.getCategoryId3()) && categoryCache.getById(sell.getCategoryId3()) != null) {
                // sell.setPageCategory(categoryCache.getById(sell.getCategoryId3()));
                // } else if (NumberUtils.isValidateNumber(sell.getCategoryId2()) && categoryCache.getById(sell.getCategoryId2()) != null) {
                // sell.setPageCategory(categoryCache.getById(sell.getCategoryId2()));
                // } else if (NumberUtils.isValidateNumber(sell.getCategoryId1()) && categoryCache.getById(sell.getCategoryId1()) != null) {
                // sell.setPageCategory(categoryCache.getById(sell.getCategoryId1()));
                // }

                String pagePeriod = StringUtils.EMPTY;
                if (productRelationCache.isDefaultPeriod(sell.getStartTime()) && productRelationCache.isDefaultPeriod(sell.getEndTime())) {
                    pagePeriod = productRelationCache.getPeriodById(sell.getStartTime()).getTitle();
                } else {
                    pagePeriod = templateService.getMessage("page.sell.list.period.separator", productRelationCache.getPeriodById(sell.getStartTime()).getTitle(), productRelationCache.getPeriodById(sell.getEndTime()).getTitle());
                }
                sell.setPagePeriod(pagePeriod);

                // time alias
                DateType dateType = DateUtils.getTimeDesc(sell.getUpdateTime());
                sell.setPageTimeAlias(templateService.getMessage("page.time.alias." + dateType.getType(), String.valueOf(dateType.getMoreThan())));

                String addr = "";
                Long cityId = sell.getCityId();
                // Long provinceId = sell.getProvinceId();
                // Long areaId = sell.getAreaId();

                // if has area, then display province and area.
                // else display province and city
                if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null) {
                    addr = areaCache.getById(cityId).getZhName();
                }
                // if (areaId != null && areaId > 0L && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
                // addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(areaId).getZhName();
                // } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
                // addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
                // } else if (areaCache.getById(provinceId) != null) {
                // addr = areaCache.getById(provinceId).getZhName();
                // }

                List<SellPic> pics = detailPicCache.getById(SellBuy.SELL, sell.getId());
                if (CollectionUtils.isNotEmpty(pics)) {
                    sell.setFirstPic(pics.get(0));
                }

                sell.setPageAddress(addr);
                
                if (sell.getCompanyId() != null && sell.getCompanyId() > 0) {
                    Company company = companyCache.getById(sell.getCompanyId());
                    if (company != null) {
                        sell.setCompanyName(company.getTitle());
                    }
                }
            }
        }
    }

    private void packageCategory(Sell sell, Long passCategoryId) {
        if (passCategoryId != null && passCategoryId > 0) {
            Map<String, Category> map = categoryCache.getSequenceCategoies(passCategoryId);
            if (MapUtils.isNotEmpty(map)) {
                for (String key : map.keySet()) {
                    try {
                        MethodUtils.invokeMethod(sell, key, map.get(key).getId());
                    } catch (Exception e) {
                        logger.error("Invoke {0} methods error.", map);
                    }
                }
            }
        }
    }

    @Override
    public Map<String, Object> findById(Sell s) {
        Map<String, Object> result = new HashMap<String, Object>();
        Sell sell = sellDAO.getById(s.getId());
        if (sell != null) {
            ProductUnit unit = productRelationCache.getUnitById(sell.getUnitId());
            sell.setPageUnit(unit);

            String pagePeriod = StringUtils.EMPTY;
            if (productRelationCache.isDefaultPeriod(sell.getStartTime()) && productRelationCache.isDefaultPeriod(sell.getEndTime())) {
                pagePeriod = productRelationCache.getPeriodById(sell.getStartTime()).getTitle();
            } else {
                pagePeriod = templateService.getMessage("page.sell.list.period.separator", productRelationCache.getPeriodById(sell.getStartTime()).getTitle(), productRelationCache.getPeriodById(sell.getEndTime()).getTitle());
            }
            sell.setPagePeriod(pagePeriod);

            // find category
            if (NumberUtils.isValidateNumber(sell.getCategoryId3()) && categoryCache.getById(sell.getCategoryId3()) != null) {
                sell.setPageCategory(categoryCache.getById(sell.getCategoryId3()));
            } else if (NumberUtils.isValidateNumber(sell.getCategoryId2()) && categoryCache.getById(sell.getCategoryId2()) != null) {
                sell.setPageCategory(categoryCache.getById(sell.getCategoryId2()));
            } else if (NumberUtils.isValidateNumber(sell.getCategoryId1()) && categoryCache.getById(sell.getCategoryId1()) != null) {
                sell.setPageCategory(categoryCache.getById(sell.getCategoryId1()));
            }

            Long cityId = sell.getCityId();
            Long provinceId = sell.getProvinceId();
            Long areaId = sell.getAreaId();
            String addr = "";

            if (areaId != null && areaId > 0L && cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
                addr = areaCache.getById(provinceId).getZhName() + (areaCache.isSpecial(provinceId) ? "市" : "省") + areaCache.getById(cityId).getZhName() + (areaCache.isSpecial(provinceId) ? "" : "市")
                        + areaCache.getById(areaId).getZhName();
            } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
                addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
            } else if (areaCache.getById(provinceId) != null) {
                addr = areaCache.getById(provinceId).getZhName();
            }

            sell.setPageAddress(addr);

            sell.setPageVarieties(varietiesCache.getById(sell.getVarietiesId()));

            if (sell.getCompanyId() != null && sell.getCompanyId() > 0) {
                Company company = companyCache.getById(sell.getCompanyId());
                if (company != null) {
                    sell.setCompanyName(company.getTitle());
                }
            }
        }
        result.put("pics", detailPicCache.getById(SellBuy.SELL, sell.getId()));
        result.put("model", sell);

        if (!ObjectUtils.equals(s.getLoginUser().getId(), null) && s.getLoginUser().getId() > 0) {
            Favourite favourite = new Favourite();
            favourite.setUserId(s.getLoginUser().getId());
            favourite.setTargetId(s.getId());
            favourite.setType(FavouriteType.SELL.getCode());
            result.put("hasFollow", favouriteService.hasFavourite(favourite));
        }
        return result;
    }

    @Override
    public Map<String, Object> findByUserId(Sell sell) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(sell, sell.getSearchCategoryId());
        List<Sell> list = sellDAO.findSells(sell);
        Integer totalCount = sellDAO.findSellCount(sell);
        Paging<Sell> result = new Paging<Sell>(totalCount, sell.getPage(), sell.getPageSize(), list);
        map.put("list", result);
        packageModel(list);
        return map;
    }

    @Override
    public int findCount(Sell sell) {
        return sellDAO.findSellCount(sell);
    }

    @Override
    public List<Category> findHotCategories() {
        List<Category> categories = new ArrayList<Category>();
        List<Long> categoryIds = sellDAO.getTopCategories("category_id_2");
        for (Long id : categoryIds) {
            categories.add(categoryCache.getById(id));
        }
        return categories;
    }

    @Override
    public Map<String, Object> addPre(Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("units", productRelationCache.getUNITS());
        map.put("periods", productRelationCache.getPERIODS());

        User user = userService.findUserById(userId);

        map.put("user", user);
        // company infos
        Paging<Company> companies = companyService.findByUserId(user);
        if (companies != null && CollectionUtils.isNotEmpty(companies.getResult())) {
            map.put("company", companies.getResult().get(0));
        }

        // user auth infos
        UserAuth userAuth = new UserAuth();
        userAuth.setUserId(user.getId());
        map.put("userAuth", userAuthService.findByUserId(user.getId()));
        map.put("commonStatus", CommonStatus.getMap());
        map.put("cvData", categoryCache.getCategoryVarierties());
        return map;
    }

}
