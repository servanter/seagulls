package com.crop.seagulls.bean;

public enum CompanyRejectEnum {

    TITLE_ERROR(1, "机构名称不存在"),

    ORGANIZATION_CODE_ERROR(2, "组织机构代码错误");

    private int code;

    private String description;

    private CompanyRejectEnum(int code, String description) {
        this.code = code;
        this.description = description;
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

    public static CompanyRejectEnum code2Rejection(int code) {
        CompanyRejectEnum[] values = CompanyRejectEnum.values();
        for (CompanyRejectEnum every : values) {
            if (every.getCode() == code) {
                return every;
            }
        }
        return null;
    }

}
