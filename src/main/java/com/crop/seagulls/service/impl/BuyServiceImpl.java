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
import com.crop.seagulls.dao.BuyDAO;
import com.crop.seagulls.entities.Buy;
import com.crop.seagulls.entities.BuyPic;
import com.crop.seagulls.entities.BuyRejection;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.Company;
import com.crop.seagulls.entities.Favourite;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.entities.SellRejection;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.AdminUserService;
import com.crop.seagulls.service.BuyPicService;
import com.crop.seagulls.service.BuyRejectionService;
import com.crop.seagulls.service.BuyService;
import com.crop.seagulls.service.CompanyService;
import com.crop.seagulls.service.FavouriteService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.service.UserAuthService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.NumberUtils;
import com.crop.seagulls.util.SecurityUtils;

@Service
public class BuyServiceImpl implements BuyService {

    private static Logger logger = Logger.getLogger(BuyServiceImpl.class);

    @Autowired
    private BuyDAO buyDAO;

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private AreaCache areaCache;

    @Autowired
    private CompanyCache companyCache;

    @Autowired
    private ProductRelationCache productRelationCache;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private FavouriteService favouriteService;

    @Autowired
    private DetailPicCache detailPicCache;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private BuyPicService buyPicService;

    @Autowired
    private VarietiesCache varietiesCache;

    @Autowired
    private BuyRejectionService buyRejectionService;

    @Autowired
    private AdminUserService adminUserService;

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

    @Override
    public Response add(Buy buy, List<String> webImagesPath) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        buy.setCreateTime(new Date());
        buy.setUnitId(productRelationCache.getDefaultUnit());
        packageCategory(buy, buy.getSearchCategoryId());
        if (ObjectUtils.notEqual(buy.getCompanyId(), null) && buy.getCompanyId() > 0) {
            Company company = companyService.findById(buy.getCompanyId());
            if (ObjectUtils.notEqual(company, null)) {
                buy.setCompanyName(company.getTitle());
            }
        }
        buyDAO.save(buy);
        if (buy.getId() == null || buy.getId() <= 0L) {
            response.setReturnCode(ReturnCode.ERROR);
        } else {
            if (CollectionUtils.isNotEmpty(webImagesPath)) {
                for (String picUrl : webImagesPath) {
                    BuyPic pic = new BuyPic();
                    pic.setBuyId(buy.getId());
                    pic.setCreateUserId(buy.getCreateUserId());
                    pic.setCreateTime(new Date());
                    pic.setImgUrl(picUrl);
                    pic.setOperatorId(buy.getCreateUserId());
                    buyPicService.add(pic);
                }
            }
            detailPicCache.refresh(SellBuy.BUY, buy.getId());
            response.setResult(buy.getId());
        }
        return response;
    }

    @Override
    public Response modify(Buy buy) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(buy);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            int affect = buyDAO.update(buy);
            if (affect <= 0) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Map<String, Object> findList(Buy buy) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(buy, buy.getSearchCategoryId());
        List<Buy> list = buyDAO.getList(buy);
        Integer totalCount = buyDAO.getListCount(buy);
        Paging<Buy> result = new Paging<Buy>(totalCount, buy.getPage(), buy.getPageSize(), list);
        packageModel(list);

        // fetch and display sub categories and
        if (buy.getSearchCategoryId() != null) {
            map.put("subCategories", categoryCache.getByPid(buy.getSearchCategoryId()));
        }
        map.put("list", result);
        map.put("topCategories", categoryCache.getByPid(Constant.CATEGORY_TOP_PID));
        return map;
    }

    @Override
    public Response ajaxFindList(Buy buy) {
        Response response = new Response(ReturnCode.SUCCESS);
        if (ObjectUtils.equals(buy.getPage(), null)) {
            buy.setPage(1);
        }
        Map<String, Object> data = findList(buy);
        if (data.containsKey("list") && CollectionUtils.isEmpty(((Paging<Buy>) data.get("list")).getResult())) {
            response.setReturnCode(ReturnCode.NO_MORE_PAGE);
        } else {
            int totalPage = ((Paging<Buy>) data.get("list")).getTotalPage();
            data.put("nextPage", buy.getPage() + 1);
        }
        response.setResult(data);
        return response;
    }

    private void packageModel(List<Buy> buies) {
        if (CollectionUtils.isNotEmpty(buies)) {
            for (Buy buy : buies) {
                addUnit(buy);

                addPeriod(buy);

                // time alias
                DateType dateType = DateUtils.getTimeDesc(buy.getRefreshTime());
                buy.setPageTimeAlias(templateService.getMessage("page.time.alias." + dateType.getType(), String.valueOf(dateType.getMoreThan())));

                String addr = "";
                Long cityId = buy.getCityId();
                if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null) {
                    addr = areaCache.getById(cityId).getZhName();
                }

                List<BuyPic> pics = detailPicCache.getById(SellBuy.BUY, buy.getId());
                if (CollectionUtils.isNotEmpty(pics)) {
                    buy.setFirstPic(pics.get(0));
                }

                buy.setPageAddress(addr);

                if (ObjectUtils.notEqual(buy.getCompanyId(), null) && buy.getCompanyId() > 0) {
                    Company company = companyCache.getById(buy.getCompanyId());
                    if (company != null) {
                        buy.setCompanyName(company.getTitle());
                    }
                }

            }
        }
    }

    private Response checkValidate(Buy buy) {
        Response result = new Response();
        result.setReturnCode(ReturnCode.SUCCESS);
        return result;
    }

    private void packageCategory(Buy buy, Long passCategoryId) {
        if (passCategoryId != null && passCategoryId > 0) {
            Map<String, Category> map = categoryCache.getSequenceCategoies(passCategoryId);
            if (MapUtils.isNotEmpty(map)) {
                for (String key : map.keySet()) {
                    try {
                        MethodUtils.invokeMethod(buy, key, map.get(key).getId());
                    } catch (Exception e) {
                        logger.error("Invoke {0} methods error.", map);
                    }
                }
            }
        }
    }

    @Override
    public Map<String, Object> findById(Buy b) {

        Map<String, Object> result = new HashMap<String, Object>();
        Buy buy = buyDAO.getById(b.getId());
        if (buy != null) {

            addUnit(buy);

            addPeriod(buy);

            addCategory(buy);

            addAddr(buy, true);

            addVarieties(buy);

            // company
            if (buy.getCompanyId() != null && buy.getCompanyId() > 0) {
                Company company = companyCache.getById(buy.getCompanyId());
                if (company != null) {
                    buy.setCompanyName(company.getTitle());
                }
                result.put("authCompany", company);
            }

            // user auth
            if (ObjectUtils.notEqual(buy.getCreateUserId(), null) && buy.getId() > 0) {
                UserAuth userAuth = userAuthService.findByUserId(buy.getCreateUserId());
                if (ObjectUtils.notEqual(userAuth, null)) {
                    result.put("authUser", userAuth);
                }
            }

            if (InfoStatus.code2Rejection(buy.getStatus()) == InfoStatus.REJECT) {
                BuyRejection reject = buyRejectionService.findByInfoId(buy.getId());
                if (ObjectUtils.notEqual(reject, null)) {
                    result.put("rejectType", BuyRejectEnum.code2Rejection(reject.getType()));
                    result.put("reject", reject);
                }
            }

            result.put("pics", detailPicCache.getById(SellBuy.BUY, buy.getId()));
        }
        result.put("model", buy);

        if (ObjectUtils.notEqual(b.getLoginUser(), null) && ObjectUtils.notEqual(b.getLoginUser().getId(), null) && b.getLoginUser().getId() > 0) {
            Favourite favourite = new Favourite();
            favourite.setUserId(b.getLoginUser().getId());
            favourite.setTargetId(b.getId());
            favourite.setType(FavouriteType.SELL.getCode());
            result.put("hasFollow", favouriteService.hasFavourite(favourite));
        }

        return result;
    }

    private void addVarieties(Buy buy) {
        buy.setPageVarieties(varietiesCache.getById(buy.getVarietiesId()));
    }

    @Override
    public Map<String, Object> findByUserId(Buy buy) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(buy, buy.getSearchCategoryId());
        List<Buy> list = buyDAO.getList(buy);
        Integer totalCount = buyDAO.getListCount(buy);
        Paging<Buy> result = new Paging<Buy>(totalCount, buy.getPage(), buy.getPageSize(), list);
        map.put("list", result);
        packageModel(list);
        return map;
    }

    @Override
    public int findCount(Buy buy) {
        return buyDAO.getListCount(buy);
    }

    @Override
    public Response ajaxFindByUserId(Buy buy) {
        Response response = new Response(ReturnCode.SUCCESS);
        if (ObjectUtils.equals(buy.getPage(), null)) {
            buy.setPage(1);
        }
        Map<String, Object> data = findList(buy);
        if (data.containsKey("list") && CollectionUtils.isEmpty(((Paging<Buy>) data.get("list")).getResult())) {
            response.setReturnCode(ReturnCode.NO_MORE_PAGE);
        } else {
            int totalPage = ((Paging<Buy>) data.get("list")).getTotalPage();
            data.put("nextPage", buy.getPage() + 1);
        }
        response.setResult(data);
        return response;
    }

    @Override
    public Response refresh(String detailIds, Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        List<Long> detailList = NumberUtils.strSplit2List(detailIds, ",", Long.class);
        if (CollectionUtils.isNotEmpty(detailList)) {
            Buy buy = new Buy();
            buy.setSearchIds(detailList);
            buy.setCreateUserId(id);
            buy.setRefreshTime(new Date());
            response = (buyDAO.batchUpdate(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS));
        }
        return response;
    }

    @Override
    public Response down(String detailIds, Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        List<Long> detailList = NumberUtils.strSplit2List(detailIds, ",", Long.class);
        if (CollectionUtils.isNotEmpty(detailList)) {
            Buy buy = new Buy();
            buy.setSearchIds(detailList);
            buy.setCreateUserId(id);
            buy.setIsPublish(false);
            response = (buyDAO.batchUpdate(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS));
        }
        return response;
    }

    @Override
    public Response on(String detailIds, Long id) {
        Response response = new Response(ReturnCode.SUCCESS);
        List<Long> detailList = NumberUtils.strSplit2List(detailIds, ",", Long.class);
        if (CollectionUtils.isNotEmpty(detailList)) {
            Buy buy = new Buy();
            buy.setSearchIds(detailList);
            buy.setCreateUserId(id);
            buy.setIsPublish(true);
            buy.setRefreshTime(new Date());
            response = (buyDAO.batchUpdate(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.SUCCESS));
        }
        return response;
    }

    @Override
    public Map<String, Object> editPre(Long id, Long createUserId) {
        Map<String, Object> result = new HashMap<String, Object>();
        Buy buy = buyDAO.getById(id);
        if (buy != null) {
            addUnit(buy);

            addPeriod(buy);

            // find category
            addCategory(buy);

            if (buy.getPageCategory() != null) {
                result.put("varieties", varietiesCache.getByCategoryId(buy.getPageCategory().getId()));
            }
            addAddr(buy);

            addVarieties(buy);

        }
        result.put("pics", detailPicCache.getById(SellBuy.BUY, buy.getId()));
        result.put("model", buy);

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
    public Response modify(Buy buy, List<String> webImagesPath) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        buy.setCreateTime(new Date());
        buy.setUnitId(productRelationCache.getDefaultUnit());
        packageCategory(buy, buy.getSearchCategoryId());
        if (ObjectUtils.notEqual(buy.getCompanyId(), null) && buy.getCompanyId() > 0) {
            Company company = companyService.findById(buy.getCompanyId());
            if (ObjectUtils.notEqual(company, null)) {
                buy.setCompanyName(company.getTitle());
            }
        }

        if (ObjectUtils.equals(buy.getStatus(), null)) {
            buy.setStatus(InfoStatus.AUDITING.getCode());
        }
        int affect = buyDAO.update(buy);
        if (affect > 0) {
            List<Long> ids = NumberUtils.strSplit2List(buy.getUpdatePicIds(), ",", Long.class);

            if (CollectionUtils.isNotEmpty(webImagesPath) && webImagesPath.size() >= ids.size()) {

                int startIndex = 0;
                // according ids update url
                for (int i = 0; i < ids.size(); i++) {
                    Long picId = ids.get(i);
                    String url = webImagesPath.get(i);

                    BuyPic buyPic = new BuyPic();
                    buyPic.setId(picId);
                    buyPic.setImgUrl(url);
                    buyPic.setOperatorId(buy.getCreateUserId());
                    buyPicService.modify(buyPic);
                    startIndex = i + 1;
                }

                // need insert new pic
                if (webImagesPath.size() > startIndex) {
                    List<String> insertImagesPath = webImagesPath.subList(startIndex, webImagesPath.size());
                    if (CollectionUtils.isNotEmpty(insertImagesPath)) {
                        for (String picUrl : insertImagesPath) {
                            BuyPic pic = new BuyPic();
                            pic.setBuyId(buy.getId());
                            pic.setCreateUserId(buy.getCreateUserId());
                            pic.setCreateTime(new Date());
                            pic.setImgUrl(picUrl);
                            pic.setOperatorId(buy.getCreateUserId());
                            buyPicService.add(pic);
                        }
                    }
                }
            }

            detailPicCache.refresh(SellBuy.BUY, buy.getId());
            response.setResult(buy.getId());
        } else {
            response.setReturnCode(ReturnCode.ERROR);
        }
        return response;
    }

    @Override
    public Map<String, Object> findAdminList(Buy buy) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(buy, buy.getSearchCategoryId());
        List<Buy> list = buyDAO.getList(buy);
        Integer totalCount = buyDAO.getListCount(buy);
        Paging<Buy> result = new Paging<Buy>(totalCount, buy.getPage(), buy.getPageSize(), list);
        packageAdminModel(list);
        map.put("list", result);
        return map;
    }

    private void packageAdminModel(List<Buy> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (Buy buy : list) {
                addUnit(buy);
                addPeriod(buy);
                addAddr(buy, true);
                addCategory(buy);
                addVarieties(buy);

                if (ObjectUtils.notEqual(buy.getAuditId(), null) && buy.getAuditId() > 0) {
                    buy.setAuditUser(adminUserService.findById(buy.getAuditId()));
                }

                // add user
                User user = userService.findUserById(buy.getCreateUserId());
                buy.setUser(user);
            }
        }
    }

    private void addAddr(Buy buy, Boolean... suffix) {
        boolean hasSuffix = false;
        if (ArrayUtils.isNotEmpty(suffix)) {
            hasSuffix = suffix[0];
        }
        Long cityId = buy.getCityId();
        Long provinceId = buy.getProvinceId();
        Long areaId = buy.getAreaId();
        String addr = "";

        if (areaId != null && areaId > 0L && cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + (hasSuffix ? (areaCache.isSpecial(provinceId) ? "市" : "省") : "") + areaCache.getById(cityId).getZhName()
                    + (hasSuffix ? (areaCache.isSpecial(provinceId) ? "" : "市") : "") + areaCache.getById(areaId).getZhName();
        } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
        } else if (areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName();
        }

        buy.setPageAddress(addr);
    }

    private void addCategory(Buy buy) {
        if (NumberUtils.isValidateNumber(buy.getCategoryId3()) && categoryCache.getById(buy.getCategoryId3()) != null) {
            buy.setPageCategory(categoryCache.getById(buy.getCategoryId3()));
        } else if (NumberUtils.isValidateNumber(buy.getCategoryId2()) && categoryCache.getById(buy.getCategoryId2()) != null) {
            buy.setPageCategory(categoryCache.getById(buy.getCategoryId2()));
        } else if (NumberUtils.isValidateNumber(buy.getCategoryId1()) && categoryCache.getById(buy.getCategoryId1()) != null) {
            buy.setPageCategory(categoryCache.getById(buy.getCategoryId1()));
        }
    }

    private void addPeriod(Buy buy) {
        String pagePeriod = StringUtils.EMPTY;
        if (productRelationCache.isDefaultPeriod(buy.getStartTime()) && productRelationCache.isDefaultPeriod(buy.getEndTime())) {
            pagePeriod = productRelationCache.getPeriodById(buy.getStartTime()).getTitle();
        } else {
            pagePeriod = templateService.getMessage("page.sell.list.period.separator", productRelationCache.getPeriodById(buy.getStartTime()).getTitle(), productRelationCache.getPeriodById(buy.getEndTime()).getTitle());
        }
        buy.setPagePeriod(pagePeriod);
    }

    private void addUnit(Buy buy) {
        ProductUnit unit = productRelationCache.getUnitById(buy.getUnitId());
        buy.setPageUnit(unit);
    }

    @Override
    public Response pass(Long id) {
        Buy buy = new Buy();
        buy.setId(id);
        buy.setStatus(InfoStatus.PASS.getCode());
        buy.setAuditId(SecurityUtils.getLoginedUserId());
        return buyDAO.update(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response reject(Long id, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        Buy buy = new Buy();
        buy.setId(id);
        buy.setStatus(InfoStatus.REJECT.getCode());
        buy.setAuditId(SecurityUtils.getLoginedUserId());
        response = buyDAO.update(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            BuyRejection rejection = new BuyRejection();
            rejection.setAuditId(SecurityUtils.getLoginedUserId());
            rejection.setInfoId(id);
            rejection.setOpinion(opinion);
            rejection.setType(type);
            response = buyRejectionService.add(rejection);
        }
        return response;
    }

    @Override
    public Response passAll(String ids) {
        Buy buy = new Buy();
        buy.setStatus(InfoStatus.PASS.getCode());
        buy.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        buy.setSearchIds(id);
        return buyDAO.batchUpdate(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response rejectAll(String ids, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        Buy buy = new Buy();
        buy.setStatus(InfoStatus.REJECT.getCode());
        buy.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        buy.setSearchIds(id);
        response = buyDAO.batchUpdate(buy) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            List<BuyRejection> rejections = new ArrayList<BuyRejection>();
            for (Long everyId : id) {
                BuyRejection rejection = new BuyRejection();
                rejection.setAuditId(SecurityUtils.getLoginedUserId());
                rejection.setInfoId(everyId);
                rejection.setOpinion(opinion);
                rejection.setType(type);
                rejections.add(rejection);
            }
            response = buyRejectionService.batchAdd(rejections);
        }
        return response;
    }

    @Override
    public Map<String, Object> findAdminById(Buy buy) {
        Map<String, Object> result = findById(buy);
        result.put("rejects", BuyRejectEnum.values());
        return result;
    }
}
