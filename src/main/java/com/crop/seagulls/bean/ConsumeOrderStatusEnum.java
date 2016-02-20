package com.crop.seagulls.bean;

public enum ConsumeOrderStatusEnum {

    NOT_PAY(0, "未支付"),
    
    /**
     * 已经支付 但还未收到第三方平台回调结果
     */
    ALREADY_PAY(10, "已经支付"),
    
    /**
     * 支付成功 已经收到第三方平台回调结果并且成功
     */
    PAY_SUCCESS(20, "支付成功"),
    
    /**
     * 支付失败 已经收到第三方平台回调结果并且失败
     */
    PAY_THIRD_PLATFORM_ERROR(21, "支付失败"),
    
    /**
     * 用户确认收货
     */
    USER_CONFIRM(50, "确认收货");

    private int code;

    private String description;

    private ConsumeOrderStatusEnum(int code, String description) {
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

    public static ConsumeOrderStatusEnum code2PayType(int code) {
        ConsumeOrderStatusEnum[] types = ConsumeOrderStatusEnum.values();
        for (ConsumeOrderStatusEnum type : types) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return ConsumeOrderStatusEnum.NOT_PAY;
    }

}
