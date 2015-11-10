package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.AreaCache;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.ProductRelationCache;
import com.crop.seagulls.common.Constant;
import com.crop.seagulls.dao.SellDAO;
import com.crop.seagulls.entities.Category;
import com.crop.seagulls.entities.ProductUnit;
import com.crop.seagulls.entities.Sell;
import com.crop.seagulls.entities.SupplyPic;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.service.SupplyPicService;
import com.crop.seagulls.service.TemplateService;
import com.crop.seagulls.util.DateType;
import com.crop.seagulls.util.DateUtils;
import com.crop.seagulls.util.Logger;

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
    private SupplyPicService supplyPicService;

    @Autowired
    private AreaCache areaCache;

    @Override
    public Response add(Sell sell, List<String> webImagesPath) {
        Response response = new Response();
        response.setReturnCode(ReturnCode.SUCCESS);
        sell.setCreateTime(new Date());
        sellDAO.save(sell);
        if (sell.getId() == null || sell.getId() <= 0L) {
            response.setReturnCode(ReturnCode.ERROR);
        } else {
            if (CollectionUtils.isNotEmpty(webImagesPath)) {
                for (String picUrl : webImagesPath) {
                    SupplyPic pic = new SupplyPic();
                    pic.setSupplyId(sell.getId());
                    pic.setCreateUserId(sell.getCreateUserId());
                    pic.setCreateTime(new Date());
                    pic.setImgUrl(picUrl);
                    pic.setOperatorId(sell.getCreateUserId());
                    supplyPicService.add(pic);
                }
            }
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

                sell.setPageAddress(addr);
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
    public Map<String, Object> findById(Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        Sell sell = sellDAO.getById(id);
        if (sell != null) {
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
        }
        result.put("pics", supplyPicService.queryBySupplyId(id));
        result.put("sell", sell);
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
}
