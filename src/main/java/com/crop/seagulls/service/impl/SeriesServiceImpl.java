package com.crop.seagulls.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.cache.FrontCache;
import com.crop.seagulls.entities.Buy;
import com.crop.seagulls.entities.Sell;
import com.crop.seagulls.service.BuyService;
import com.crop.seagulls.service.SellService;
import com.crop.seagulls.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private SellService sellService;
    
    @Autowired
    private BuyService buyService;
    
    @Autowired
    private FrontCache frontCache;
    
    @Override
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<String, Object>();
        Sell sell = new Sell();
        sell.setIsPublish(true);
        sell.setIsValid(true);
        
        int totalSell = sellService.findCount(sell);
        
        Buy buy = new Buy();
        buy.setIsPublish(true);
        buy.setIsValid(true);
        
        int totalBuy = buyService.findCount(buy);
        
        map.put("totalSell", totalSell);
        map.put("totalBuy", totalBuy);
        map.put("hotCategories", frontCache.getHotCategories());
        map.put("banners", frontCache.getIndexBanners());
        return map;
    }

}
