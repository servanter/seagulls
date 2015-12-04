package com.crop.seagulls.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crop.seagulls.bean.SellBuy;
import com.crop.seagulls.entities.BuyPic;
import com.crop.seagulls.entities.SellPic;
import com.crop.seagulls.service.BuyPicService;
import com.crop.seagulls.service.SellPicService;

@Component
public class DetailPicCache {

    private static Map<Long, List<SellPic>> SELL_PICS = new HashMap<Long, List<SellPic>>();
    private static Map<Long, List<BuyPic>> BUY_PICS = new HashMap<Long, List<BuyPic>>();
    private static Map<SellBuy, Object> PICS = new HashMap<SellBuy, Object>();

    @Autowired
    private SellPicService sellPicService;

    @Autowired
    private BuyPicService buyPicService;

    // @Scheduled(cron = "0 0 * * * ?")
    @PostConstruct
    public void init() {
        List<SellPic> sellPics = sellPicService.findAll();
        if (CollectionUtils.isNotEmpty(sellPics)) {
            Map<Long, List<SellPic>> map = new HashMap<Long, List<SellPic>>();
            for (SellPic sellPic : sellPics) {
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

        List<BuyPic> buyPics = buyPicService.findAll();
        if (CollectionUtils.isNotEmpty(buyPics)) {
            Map<Long, List<BuyPic>> map = new HashMap<Long, List<BuyPic>>();
            for (BuyPic buyPic : buyPics) {
                if (map.containsKey(buyPic.getBuyId())) {
                    map.get(buyPic.getBuyId()).add(buyPic);
                } else {
                    List<BuyPic> buySell = new ArrayList<BuyPic>();
                    buySell.add(buyPic);
                    map.put(buyPic.getBuyId(), buySell);
                }
            }
            BUY_PICS = map;
        }

        PICS.put(SellBuy.SELL, SELL_PICS);
        PICS.put(SellBuy.BUY, BUY_PICS);
    }

    public void refresh(SellBuy sellBuy, Long id) {
        switch (sellBuy) {
        case SELL:
            List<SellPic> pics = sellPicService.findBySellId(id);
            if (CollectionUtils.isNotEmpty(pics)) {
                SELL_PICS.put(id, pics);
            }
            break;
        case BUY:
            List<BuyPic> ps = buyPicService.findByBuyId(id);
            if (CollectionUtils.isNotEmpty(ps)) {
                BUY_PICS.put(id, ps);
            }
            break;
        }
    }

    public <T> List<T> getById(SellBuy buySell, Long id) {
        if (((Map<Long, Map<Long, List<T>>>) PICS.get(buySell)).containsKey(id)) {
            return ((List<T>) ((Map<Long, Map<Long, List<T>>>) PICS.get(buySell)).get(id));
        }
        return null;
    }

}