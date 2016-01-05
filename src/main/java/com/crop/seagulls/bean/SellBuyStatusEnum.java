package com.crop.seagulls.bean;

import java.util.HashMap;
import java.util.Map;

public enum SellBuyStatusEnum {

    REJECT(-1, "审核未通过"),

    AUDITING(0, "正在审核"),

    PASS(1, "审核通过");

    private static Map<String, SellBuyStatusEnum> map = new HashMap<String, SellBuyStatusEnum>();

    static {
        map.put(SellBuyStatusEnum.REJECT.name(), SellBuyStatusEnum.REJECT);
        map.put(SellBuyStatusEnum.AUDITING.name(), SellBuyStatusEnum.AUDITING);
        map.put(SellBuyStatusEnum.PASS.name(), SellBuyStatusEnum.PASS);
    }

    private int code;

    private String description;

    private SellBuyStatusEnum(int code, String desciption) {
        this.code = code;
        this.description = desciption;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Map<String, SellBuyStatusEnum> getMap() {
        return map;
    }

    public static void setMap(Map<String, SellBuyStatusEnum> map) {
        SellBuyStatusEnum.map = map;
    }

}
