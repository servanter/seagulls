package com.crop.seagulls.bean;

import java.util.HashMap;
import java.util.Map;

public enum InfoStatus {

    NULL(-999,"",""),
    REJECT(-1, "审核未通过", "您的图片不符合要求，请重新上传"),

    AUDITING(0, "正在审核", "我们会尽快进行审核，请耐心等待"),

    PASS(1, "审核通过", "内容如有修改，需要重新审核");

    private static Map<String, InfoStatus> map = new HashMap<String, InfoStatus>();

    static {
        map.put(InfoStatus.REJECT.name(), InfoStatus.REJECT);
        map.put(InfoStatus.AUDITING.name(), InfoStatus.AUDITING);
        map.put(InfoStatus.PASS.name(), InfoStatus.PASS);
    }

    private int code;

    private String description;

    private String moreDescription;

    private InfoStatus(int code, String desciption, String moreDescription) {
        this.code = code;
        this.description = desciption;
        this.moreDescription = moreDescription;
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

    public String getMoreDescription() {
        return moreDescription;
    }

    public void setMoreDescription(String moreDescription) {
        this.moreDescription = moreDescription;
    }

    public static Map<String, InfoStatus> getMap() {
        return map;
    }

    public static void setMap(Map<String, InfoStatus> map) {
        InfoStatus.map = map;
    }

    public static InfoStatus code2Rejection(int code) {
        InfoStatus[] values = InfoStatus.values();
        for (InfoStatus every : values) {
            if (every.getCode() == code) {
                return every;
            }
        }
        return InfoStatus.NULL;
    }

}
