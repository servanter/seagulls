package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.bean.BuySell;
import com.crop.seagulls.entities.SellPic;
import com.crop.seagulls.service.SellPicService;

@Component
public class DetailPicCache {

    private static Map<Long, List<SellPic>> SELL_PICS = new HashMap<Long, List<SellPic>>();
    private static Map<BuySell, Map<Long, List<SellPic>>> PICS = new HashMap<BuySell, Map<Long, List<SellPic>>>();

    @Autowired
    private SellPicService sellPicService;

    // @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        List<SellPic> pics = sellPicService.findAll();
        if (CollectionUtils.isNotEmpty(pics)) {
            Map<Long, List<SellPic>> map = new HashMap<Long, List<SellPic>>();
            for (SellPic sellPic : pics) {
                if (map.containsKey(sellPic.getSellId())) {
                    map.get(sellPic.getSellId()).add(sellPic);
                } else {
                    List<SellPic> curSell = new ArrayList<SellPic>();
                    curSell.add(sellPic);
                    map.put(sellPic.getSellId(), curSell);
                }
            }
            SELL_PICS = map;
        }
        PICS.put(BuySell.SELL, SELL_PICS);
    }

    public List<SellPic> getById(BuySell buySell, Long id) {
        if (PICS.get(buySell).containsKey(id)) {
            return PICS.get(buySell).get(id);
        }
        return null;
    }

}