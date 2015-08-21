package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.AreaCache;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.dao.BuyDAO;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.entities.Buy;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.service.BuyService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.Logger;
import com.crop.seagulls.util.NumberUtils;
import com.crop.seagulls.util.TextUtils;

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
    private ProductRelationCache productRelationCache;

    @Autowired
    private TemplateService templateService;

    @Override
    public Response add(Buy buy) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(buy);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            buy.setCreateTime(new Date());
            buyDAO.save(buy);
            if (buy.getId() == null || buy.getId() <= 0L) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Response modifyBuy(Buy buy) {
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
    public Map<String, Object> querySuppliesWithExt(Buy buy) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(buy, buy.getSearchCategoryId());
        List<Buy> list = buyDAO.findBuies(buy);
        Integer totalCount = buyDAO.findBuiesCount(buy);
        Paging<Buy> result = new Paging<Buy>(totalCount, buy.getPage(), buy.getPageSize(), list);
        List<Long> areaIds = buyDAO.findAllProvincesByBuy(buy);
        List<Area> areas = new ArrayList<Area>();
        if (CollectionUtils.isNotEmpty(areaIds)) {
            for (Long areaId : areaIds) {
                Area area = areaCache.getById(areaId);
                if (area != null) {
                    areas.add(area);
                }
            }
        }
        map.put("list", result);
        map.put("areas", areas);
        map.put("periods", productRelationCache.getPERIODS());
        packageModel(list);
        packageSearchModel(buy);
        return map;
    }

    private void packageSearchModel(Buy buy) {
        buy.setSearchArea(areaCache.getById(buy.getProvinceId()));
        Map<String, Category> map = categoryCache.getSequenceCategoies(buy.getSearchCategoryId(), 2);
        if (MapUtils.isNotEmpty(map)) {
            for (String key : map.keySet()) {
                try {
                    MethodUtils.invokeMethod(buy, key, map.get(key));
                } catch (Exception e) {
                    logger.error("Invoke {0} methods error.", map);
                }
            }
        }
        if (productRelationCache.getPeriodById(buy.getStartTime()) != null) {
            buy.setSearchStartTime(productRelationCache.getPeriodById(buy.getStartTime()));
        }
    }

    private void packageModel(List<Buy> buies) {
        if (CollectionUtils.isNotEmpty(buies)) {
            for (Buy buy : buies) {
                ProductUnit unit = productRelationCache.getUnitById(buy.getUnitId());
                buy.setPagePrice(templateService.getMessage("page.product.price", buy.getStartPrice().toString(), buy.getEndPrice().toString(), unit.getTitle()));

                ProductUnit buyUnit = productRelationCache.getUnitById(buy.getBuyUnitId());
                buy.setPageBuyUnit(buyUnit);
                // find category
                if (NumberUtils.isValidateNumber(buy.getCategoryId3()) && categoryCache.getById(buy.getCategoryId3()) != null) {
                    buy.setPageCategory(categoryCache.getById(buy.getCategoryId3()));
                } else if (NumberUtils.isValidateNumber(buy.getCategoryId2()) && categoryCache.getById(buy.getCategoryId2()) != null) {
                    buy.setPageCategory(categoryCache.getById(buy.getCategoryId2()));
                } else if (NumberUtils.isValidateNumber(buy.getCategoryId1()) && categoryCache.getById(buy.getCategoryId1()) != null) {
                    buy.setPageCategory(categoryCache.getById(buy.getCategoryId1()));
                }

                buy.setPageStartPeriod(productRelationCache.getPeriodById(buy.getStartTime()));
                buy.setPageEndPeriod(productRelationCache.getPeriodById(buy.getEndTime()));

                DateType dateType = DateUtils.getTimeDesc(buy.getUpdateTime());
                buy.setPageTimeAlias(templateService.getMessage("page.time_alias_" + dateType.getType(), String.valueOf(dateType.getMoreThan())));
            }
        }
    }

    private Response checkValidate(Buy buy) {
        Response result = new Response();
        result.setReturnCode(ReturnCode.SUCCESS);
        packageCategory(buy, buy.getAddCategoryId());
        return result;
    }

    private void packageCategory(Buy buy, Long passCategoryId) {
        if (passCategoryId != null && passCategoryId > 0) {
            Map<String, Category> map = categoryCache.getSequenceCategoies(passCategoryId, 1);
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
    public Paging<Buy> queryBuies(Buy buy) {
        List<Buy> list = buyDAO.findBuies(buy);
        Integer totalCount = buyDAO.findBuiesCount(buy);
        return new Paging<Buy>(totalCount, buy.getPage(), buy.getPageSize(), list);
    }

    @Override
    public Map<String, Object> findById(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Buy buy = buyDAO.getById(id);
        map.put("buy", buy);
        initAttr(buy);
        buy.setPageStartPeriod(productRelationCache.getPeriodById(buy.getStartTime()));
        buy.setPageEndPeriod(productRelationCache.getPeriodById(buy.getEndTime()));
        
        buy.setPageUnit(productRelationCache.getUnitById(buy.getUnitId()));
        buy.setPageBuyUnit(productRelationCache.getUnitById(buy.getBuyUnitId()));
        
        buy.setPageStartPeriod(productRelationCache.getPeriodById(buy.getStartTime()));
        buy.setPageEndPeriod(productRelationCache.getPeriodById(buy.getEndTime()));

        packageSearchModel(buy);
        buy.setPageQuantity(TextUtils.removeEndZero(buy.getQuantity().toString()));
        
        long category = -1;
        // find category
        if (NumberUtils.isValidateNumber(buy.getCategoryId3()) && categoryCache.getById(buy.getCategoryId3()) != null) {
            buy.setPageCategory(categoryCache.getById(buy.getCategoryId3()));
            category = categoryCache.getById(buy.getCategoryId3()).getId();
        } else if (NumberUtils.isValidateNumber(buy.getCategoryId2()) && categoryCache.getById(buy.getCategoryId2()) != null) {
            buy.setPageCategory(categoryCache.getById(buy.getCategoryId2()));
            category = categoryCache.getById(buy.getCategoryId2()).getId();
        } else if (NumberUtils.isValidateNumber(buy.getCategoryId1()) && categoryCache.getById(buy.getCategoryId1()) != null) {
            buy.setPageCategory(categoryCache.getById(buy.getCategoryId1()));
            category = categoryCache.getById(buy.getCategoryId1()).getId();
        }
        
        Map<String, Category> methodMap = categoryCache.getSequenceCategoies(category, 2);
        if (MapUtils.isNotEmpty(methodMap)) {
            for (String key : methodMap.keySet()) {
                try {
                    MethodUtils.invokeMethod(buy, key, methodMap.get(key));
                } catch (Exception e) {
                    logger.error("Invoke {0} methods error.", methodMap);
                }
            }
        }
        return map;
    }
    
    private void initAttr(Buy buy) {
        String addr = "";
        Long provinceId = buy.getProvinceId();
        Long cityId = buy.getCityId();
        Long areaId = buy.getAreaId();

        // if has area, then display province and area.
        // else display province and city
        if (areaId != null && areaId > 0L && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(areaId).getZhName();
        } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
        } else if (areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName();
        }
        buy.setPageOriginAddr(addr);
    }

}
