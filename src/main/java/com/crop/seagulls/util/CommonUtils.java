package com.crop.seagulls.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.cache.AreaCache;

@Component
public class CommonUtils {

    @Autowired
    private AreaCache areaCache;

    public String addAddr(Long provinceId, Long cityId, Long areaId, Boolean... suffix) {
        boolean hasSuffix = false;
        if (ArrayUtils.isNotEmpty(suffix)) {
            hasSuffix = suffix[0];
        }
        String addr = "";

        if (areaId != null && areaId > 0L && cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(areaId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + (hasSuffix ? (areaCache.isSpecial(provinceId) ? "市" : "省") : "") + areaCache.getById(cityId).getZhName()
                    + (hasSuffix ? (areaCache.isSpecial(provinceId) ? "" : "市") : "") + areaCache.getById(areaId).getZhName();
        } else if (cityId != null && cityId > 0L && areaCache.getById(cityId) != null && areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName() + areaCache.getById(cityId).getZhName();
        } else if (areaCache.getById(provinceId) != null) {
            addr = areaCache.getById(provinceId).getZhName();
        }
        return addr;
    }
}
