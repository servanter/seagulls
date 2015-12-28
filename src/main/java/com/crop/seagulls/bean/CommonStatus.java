package com.crop.seagulls.bean;

import java.util.HashMap;
import java.util.Map;

public enum CommonStatus {

    REJECT(-2, "审核未通过"),
    
    UN_SUBMIT(-1, "未提交"),

    AUDITING(0, "正在审核"),

    PASS(1, "审核通过");

    private static Map<String, CommonStatus> map = new HashMap<String, CommonStatus>();

    static {
        map.put(CommonStatus.REJECT.name(), CommonStatus.REJECT);
        map.put(CommonStatus.UN_SUBMIT.name(), CommonStatus.UN_SUBMIT);
        map.put(CommonStatus.AUDITING.name(), CommonStatus.AUDITING);
        map.put(CommonStatus.PASS.name(), CommonStatus.PASS);
    }
    
    private int code;

    private String description;

    private CommonStatus(int code, String desciption) {
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

    public static Map<String, CommonStatus> getMap() {
        return map;
    }

    public static void setMap(Map<String, CommonStatus> map) {
        CommonStatus.map = map;
    }

}
