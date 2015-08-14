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

import com.crop.seagulls.bean.OrderBy;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.AreaCache;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.dao.SupplyDAO;
import com.crop.seagulls.entities.Area;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.entities.Supply;
import com.crop.seagulls.service.SupplyService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.Logger;

@Service
public class SupplyServiceImpl implements SupplyService {

    private static Logger logger = Logger.getLogger(SupplyServiceImpl.class);

    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private AreaCache areaCache;

    @Autowired
    private ProductRelationCache productRelationCache;

    @Autowired
    private SupplyDAO supplyDAO;

    @Autowired
    private TemplateService templateService;

    @Override
    public Response add(Supply supply) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(supply);
        supply.setCreateTime(new Date());
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            supplyDAO.save(supply);
            if (supply.getId() == null || supply.getId() <= 0L) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Response modifySupply(Supply supply) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        Response checkResponse = checkValidate(supply);
        if (ReturnCode.isSuccess(checkResponse.getReturnCode())) {
            int affect = supplyDAO.update(supply);
            if (affect <= 0) {
                response.setReturnCode(ReturnCode.ERROR);
            }
        } else {
            response = checkResponse;
        }
        return response;
    }

    @Override
    public Map<String, Object> querySuppliesWithExt(Supply supply) {
        Map<String, Object> map = new HashMap<String, Object>();
        packageCategory(supply);
        List<Supply> list = supplyDAO.findSupplies(supply);
        Integer totalCount = supplyDAO.findSuppliesCount(supply);
        Paging<Supply> result = new Paging<Supply>(totalCount, supply.getPage(), supply.getPageSize(), list);
        List<Long> areaIds = supplyDAO.findAllProvincesBySupply(supply);
        List<Area> areas = new ArrayList<Area>();
        if (CollectionUtils.isNotEmpty(areaIds)) {
            for (Long areaId : areaIds) {
                Area area = areaCache.getById(areaId);
                if (area != null) {
                    areas.add(area);
                }
            }
        }
        map.put("result", result);
        map.put("areas", areas);
        packageModel(list);
        packageSearchModel(supply);
        return map;
    }

    private void packageSearchModel(Supply supply) {
        supply.setPageArea(areaCache.getById(supply.getProvinceId()));
        supply.setPageOrderBy(OrderBy.code2OrderBy(supply.getSearchOrderBy()));
    }

    private void packageModel(List<Supply> suppies) {
        if (CollectionUtils.isNotEmpty(suppies)) {
            for (Supply supply : suppies) {
                ProductUnit unit = productRelationCache.getUnitById(supply.getUnitId());
                supply.setPagePrice(templateService.getMessage("page.product.price", supply.getStartPrice().toString(), supply.getEndPrice().toString(), unit.getTitle()));

                String addr = "";
                Long provinceId = supply.getProvinceId();
                Long cityId = supply.getCityId();
                Long areaId = supply.getAreaId();

                // if has area, then display province and area.
                // else display province and city
                if (areaId != null && areaId > 0L && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
                    addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(areaId).getZhName();
                } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
                    addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
                } else if (areaCache.getById(provinceId) != null) {
                    addr = areaCache.getById(provinceId).getZhName();
                }
                supply.setPageOriginAddr(addr);

                DateType dateType = DateUtils.getTimeDesc(supply.getUpdateTime());
                supply.setPageTimeAlias(templateService.getMessage("page.time_alias_" + dateType.getType(), String.valueOf(dateType.getMoreThan())));
            }
        }
    }

    private Response checkValidate(Supply supply) {
        Response result = new Response();
        result.setReturnCode(ReturnCode.SUCCESS);
        packageCategory(supply);
        return result;
    }

    private void packageCategory(Supply supply) {
        if (supply.getSearchCategoryId() != null && supply.getSearchCategoryId() > 0) {
            Map<String, Long> map = categoryCache.getSequenceCategoies(supply.getSearchCategoryId());
            if (MapUtils.isNotEmpty(map)) {
                for (String key : map.keySet()) {
                    Long categoryId = map.get(key);
                    try {
                        MethodUtils.invokeMethod(supply, key, categoryId);
                    } catch (Exception e) {
                        logger.error("Invoke {0} methods error.", map);
                    }
                }
            }
        }
    }
}
