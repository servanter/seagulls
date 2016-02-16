package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.BuyRejectEnum;
import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.FavouriteType;
import com.crop.seagulls.bean.InfoStatus;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.bean.SellBuy;
import com.crop.seagulls.bean.SellRejectEnum;
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
import com.crop.seagulls.entities.SellRejection;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.AddressService;
import com.crop.seagulls.service.AdminUserService;
import com.crop.seagulls.service.CompanyService;
import com.crop.seagulls.service.FavouriteService;
import com.crop.seagulls.service.SellPicService;
import com.crop.seagulls.service.SellRejectionService;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserAuthService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.NumberUtils;
import com.crop.seagulls.util.SecurityUtils;

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

    @Autowired
    private SellRejectionService sellRejectionService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AddressService addressService;

    @Override
    public Response add(Sell sell, List<String> webImagesPath) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        sell.setCreateTime(new Date());
        sell.setUnitId(productRelationCache.getDefaultUnit());
        packageCategory(sell, sell.getSearchCategoryId());
        if (ObjectUtils.notEqual(sell.getCompanyId(), null) && sell.getCompanyId() > 0) {
            Company company = companyService.findById(sell.getCompanyId());
            if (ObjectUtils.notEqual(company, null)) {
                sell.setCompanyName(company.getTitle());
            }
        }
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
        List<Sell> list = sellDAO.getList(sell);
        Integer totalCount = sellDAO.getListCount(sell);
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

                addUnit(sell);

                // find category
                // if (NumberUtils.isValidateNumber(sell.getCategoryId3()) && categoryCache.getById(sell.getCategoryId3()) != null) {
                // sell.setPageCategory(categoryCache.getById(sell.getCategoryId3()));
                // } else if (NumberUtils.isValidateNumber(sell.getCategoryId2()) && categoryCache.getById(sell.getCategoryId2()) != null) {
                // sell.setPageCategory(categoryCache.getById(sell.getCategoryId2()));
                // } else if (NumberUtils.isValidateNumber(sell.getCategoryId1()) && categoryCache.getById(sell.getCategoryId1()) != null) {
                // sell.setPageCategory(categoryCache.getById(sell.getCategoryId1()));
                // }

                addPeriod(sell);

                // time alias
                DateType dateType = DateUtils.getTimeDesc(sell.getRefreshTime());
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
            addUnit(sell);

            addPeriod(sell);

            // find category
            addCategory(sell);

            addAddr(sell, true);

            addVarieties(sell);

            // company
            if (sell.getCompanyId() != null && sell.getCompanyId() > 0) {
                Company company = companyCache.getById(sell.getCompanyId());
                if (company != null) {
                    sell.setCompanyName(company.getTitle());
                }
                result.put("authCompany", company);
            }

            // user auth
            if (ObjectUtils.notEqual(sell.getCreateUserId(), null) && sell.getId() > 0) {
                UserAuth userAuth = userAuthService.findByUserId(sell.getCreateUserId());
                if (ObjectUtils.notEqual(userAuth, null)) {
                    result.put("authUser", userAuth);
                }
            }

            if (InfoStatus.code2Rejection(sell.getStatus()) == InfoStatus.REJECT) {
                SellRejection reject = sellRejectionService.findByInfoId(sell.getId());
                if (ObjectUtils.notEqual(reject, null)) {
                    result.put("rejectType", SellRejectEnum.code2Rejection(reject.getType()));
                    result.put("reject", reject);
                }
            }
            result.put("pics", detailPicCache.getById(SellBuy.SELL, sell.getId()));
        }
        result.put("model", sell);

        if (ObjectUtils.notEqual(s.getLoginUser(), null) && ObjectUtils.notEqual(s.getLoginUser().getId(), null) && s.getLoginUser().getId() > 0) {
            Favourite favourite = new Favourite();
            favourite.setUserId(s.getLoginUser().getId());
            favourite.setTargetId(s.getId());
            favourite.setType(FavouriteType.SELL.getCode());
            result.put("hasFollow", favouriteService.hasFavourite(favourite));
        }

        return result;
    }

    @Override
    public Sell findBaseInfoById(Long sellId) {
        Sell sell = sellDAO.getById(sellId);
        if (ObjectUtils.notEqual(sell, null)) {
            addUnit(sell);

            // pic
            List<SellPic> pics = detailPicCache.getById(SellBuy.SELL, sell.getId());
            if (CollectionUtils.isNotEmpty(pics)) {
                sell.setFirstPic(pics.get(0));
            }
        }
        return sell;
    }

    private void addVarieties(Sell sell) {
        sell.setPageVarieties(varietiesCache.getById(sell.getVarietiesId()));
    }

    private void addAddr(Sell sell, Boolean... suffix) {
        boolean hasSuffix = false;
        if (ArrayUtils.isNotEmpty(suffix)) {
            hasSuffix = suffix[0];
        }
        Long cityId = sell.getCityId();
        Long provinceId = sell.getProvinceId();
        Long areaId = sell.getAreaId();
        String addr = "";

        if (areaId != null && areaId > 0L && cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + (hasSuffix ? (areaCache.isSpecial(provinceId) ? "市" : "省") : "") + areaCache.getById(cityId).getZhName()
                    + (hasSuffix ? (areaCache.isSpecial(provinceId) ? "" : "市") : "") + areaCache.getById(areaId).getZhName();
        } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
        } else if (areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName();
        }

        sell.setPageAddress(addr);
    }

    private void addCategory(Sell sell) {
        if (NumberUtils.isValidateNumber(sell.getCategoryId3()) && categoryCache.getById(sell.getCategoryId3()) != null) {
            sell.setPageCategory(categoryCache.getById(sell.getCategoryId3()));
        } else if (NumberUtils.isValidateNumber(sell.getCategoryId2()) && categoryCache.getById(sell.getCategoryId2()) != null) {
            sell.setPageCategory(categoryCache.getById(sell.getCategoryId2()));
        } else if (NumberUtils.isValidateNumber(sell.getCategoryId1()) && categoryCache.getById(sell.getCategoryId1()) != null) {
            sell.setPageCategory(categoryCache.getById(sell.getCategoryId1()));
        }
    }

    private void addPeriod(Sell sell) {
        String pagePeriod = StringUtils.EMPTY;
        if (productRelationCache.isDefaultPeriod(sell.getStartTime()) && productRelationCache.isDefaultPeriod(sell.getEndTime())) {
            pagePeriod = productRelationCache.getPeriodById(sell.getStartTime()).getTitle();
        } else {
            pagePeriod = templateService.getMessage("page.sell.list.period.separator", productRelationCache.getPeriodById(sell.getStartTime()).getTitle(), productRelationCache.getPeriodById(sell.getEndTime()).getTitle());
        }
        sell.setPagePeriod(pagePeriod);
    }

    private void addUnit(Sell sell) {
        ProductUnit unit = productRelationCache.getUnitById(sell.getUnitId());
        sell.setPageUnit(unit);
    }

    @Override
    public Map<String, Object> findByUserId(Sell sell) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(sell, sell.getSearchCategoryId());
        List<Sell> list = sellDAO.getList(sell);
        Integer totalCount = sellDAO.getListCount(sell);
        Paging<Sell> result = new Paging<Sell>(totalCount, sell.getPage(), sell.getPageSize(), list);
        map.put("list", result);
        packageModel(list);
        return map;
    }

    @Override
    public Response ajaxFindByUserId(Sell sell) {
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

    @Override
    public int findCount(Sell sell) {
        return sellDAO.getListCount(sell);
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
        return map;
    }

    @Override
    public Response refresh(String detailIds, Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        List<Long> detailList = NumberUtils.strSplit2List(detailIds, ",", Long.class);
        if (CollectionUtils.isNotEmpty(detailList)) {
            Sell sell = new Sell();
            sell.setSearchIds(detailList);
            sell.setCreateUserId(id);
            sell.setRefreshTime(new Date());
            response = (sellDAO.batchUpdate(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS));
        }
        return response;
    }

    @Override
    public Response down(String detailIds, Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        List<Long> detailList = NumberUtils.strSplit2List(detailIds, ",", Long.class);
        if (CollectionUtils.isNotEmpty(detailList)) {
            Sell sell = new Sell();
            sell.setSearchIds(detailList);
            sell.setCreateUserId(id);
            sell.setIsPublish(false);
            response = (sellDAO.batchUpdate(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS));
        }
        return response;
    }

    @Override
    public Response on(String detailIds, Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        List<Long> detailList = NumberUtils.strSplit2List(detailIds, ",", Long.class);
        if (CollectionUtils.isNotEmpty(detailList)) {
            Sell sell = new Sell();
            sell.setSearchIds(detailList);
            sell.setCreateUserId(id);
            sell.setIsPublish(true);
            sell.setRefreshTime(new Date());
            response = (sellDAO.batchUpdate(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS));
        }
        return response;
    }

    @Override
    public Map<String, Object> editPre(Long id, Long createUserId) {
        Map<String, Object> result = new HashMap<String, Object>();
        Sell sell = sellDAO.getById(id);
        if (sell != null) {
            addUnit(sell);

            addPeriod(sell);

            addCategory(sell);

            if (sell.getPageCategory() != null) {
                result.put("varieties", varietiesCache.getByCategoryId(sell.getPageCategory().getId()));
            }

            addAddr(sell);

            addVarieties(sell);

        }
        result.put("pics", detailPicCache.getById(SellBuy.SELL, sell.getId()));
        result.put("model", sell);

        result.put("units", productRelationCache.getUNITS());
        result.put("periods", productRelationCache.getPERIODS());

        User user = userService.findUserById(createUserId);

        result.put("user", user);
        // company infos
        Paging<Company> companies = companyService.findByUserId(user);
        if (companies != null && CollectionUtils.isNotEmpty(companies.getResult())) {
            result.put("company", companies.getResult().get(0));
        }

        result.put("commonStatus", CommonStatus.getMap());

        return result;
    }

    @Override
    public Response modify(Sell sell, List<String> webImagesPath) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        sell.setCreateTime(new Date());
        sell.setUnitId(productRelationCache.getDefaultUnit());
        packageCategory(sell, sell.getSearchCategoryId());
        if (ObjectUtils.notEqual(sell.getCompanyId(), null) && sell.getCompanyId() > 0) {
            Company company = companyService.findById(sell.getCompanyId());
            if (ObjectUtils.notEqual(company, null)) {
                sell.setCompanyName(company.getTitle());
            }
        }

        if (ObjectUtils.equals(sell.getStatus(), null)) {
            sell.setStatus(InfoStatus.AUDITING.getCode());
        }
        int affect = sellDAO.update(sell);
        if (affect > 0) {
            List<Long> ids = NumberUtils.strSplit2List(sell.getUpdatePicIds(), ",", Long.class);

            if (CollectionUtils.isNotEmpty(webImagesPath) && webImagesPath.size() >= ids.size()) {

                int startIndex = 0;
                // according ids update url
                for (int i = 0; i < ids.size(); i++) {
                    Long picId = ids.get(i);
                    String url = webImagesPath.get(i);

                    SellPic sellPic = new SellPic();
                    sellPic.setId(picId);
                    sellPic.setImgUrl(url);
                    sellPic.setOperatorId(sell.getCreateUserId());
                    sellPicService.modify(sellPic);
                    startIndex = i + 1;
                }

                // need insert new pic
                if (webImagesPath.size() > startIndex) {
                    List<String> insertImagesPath = webImagesPath.subList(startIndex, webImagesPath.size());
                    if (CollectionUtils.isNotEmpty(insertImagesPath)) {
                        for (String picUrl : insertImagesPath) {
                            SellPic pic = new SellPic();
                            pic.setSellId(sell.getId());
                            pic.setCreateUserId(sell.getCreateUserId());
                            pic.setCreateTime(new Date());
                            pic.setImgUrl(picUrl);
                            pic.setOperatorId(sell.getCreateUserId());
                            sellPicService.add(pic);
                        }
                    }
                }
            }

            detailPicCache.refresh(SellBuy.SELL, sell.getId());
            response.setResult(sell.getId());
        } else {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

    @Override
    public Map<String, Object> findAdminList(Sell sell) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(sell, sell.getSearchCategoryId());
        List<Sell> list = sellDAO.getList(sell);
        Integer totalCount = sellDAO.getListCount(sell);
        Paging<Sell> result = new Paging<Sell>(totalCount, sell.getPage(), sell.getPageSize(), list);
        packageAdminModel(list);
        map.put("list", result);
        return map;
    }

    private void packageAdminModel(List<Sell> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Sell sell : list) {
                addUnit(sell);
                addPeriod(sell);
                addAddr(sell, true);
                addCategory(sell);
                addVarieties(sell);

                if (ObjectUtils.notEqual(sell.getAuditId(), null) && sell.getAuditId() > 0) {
                    sell.setAuditUser(adminUserService.findById(sell.getAuditId()));
                }

                // add user
                User user = userService.findUserById(sell.getCreateUserId());
                sell.setUser(user);
            }
        }
    }

    @Override
    public Response pass(Long id) {
        Sell sell = new Sell();
        sell.setId(id);
        sell.setStatus(InfoStatus.PASS.getCode());
        sell.setAuditId(SecurityUtils.getLoginedUserId());
        return sellDAO.update(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response reject(Long id, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        Sell sell = new Sell();
        sell.setId(id);
        sell.setStatus(InfoStatus.REJECT.getCode());
        sell.setAuditId(SecurityUtils.getLoginedUserId());
        response = sellDAO.update(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            SellRejection rejection = new SellRejection();
            rejection.setAuditId(SecurityUtils.getLoginedUserId());
            rejection.setInfoId(id);
            rejection.setOpinion(opinion);
            rejection.setType(type);
            response = sellRejectionService.add(rejection);
        }
        return response;
    }

    @Override
    public Response passAll(String ids) {
        Sell sell = new Sell();
        sell.setStatus(InfoStatus.PASS.getCode());
        sell.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        sell.setSearchIds(id);
        return sellDAO.batchUpdate(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response rejectAll(String ids, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        Sell sell = new Sell();
        sell.setStatus(InfoStatus.REJECT.getCode());
        sell.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        sell.setSearchIds(id);
        response = sellDAO.batchUpdate(sell) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            List<SellRejection> rejections = new ArrayList<SellRejection>();
            for (Long everyId : id) {
                SellRejection rejection = new SellRejection();
                rejection.setAuditId(SecurityUtils.getLoginedUserId());
                rejection.setInfoId(everyId);
                rejection.setOpinion(opinion);
                rejection.setType(type);
                rejections.add(rejection);
            }
            response = sellRejectionService.batchAdd(rejections);
        }
        return response;
    }

    @Override
    public Map<String, Object> findAdminById(Sell sell) {
        Map<String, Object> result = findById(sell);
        result.put("rejects", BuyRejectEnum.values());
        return result;
    }

    @Override
    public Map<String, Object> purchasePre(Long sellId, Long userId) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("model", findBaseInfoById(sellId));
        result.put("addresses", addressService.findUserAddress(userId));
        return result;
    }

}
