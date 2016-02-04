package com.crop.seagulls.bean.wx;

/**
 * �¼�����
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public enum Event {

    /**
     * 订阅
     */
    SUBSCRIBE(1),

    /**
     * 退订
     */
    UNSUBSCRIBE(2),

    /**
     * 已经关注
     */
    SCAN(3),

    LOCATION(4),

    /**
     * 点击
     */
    CLICK(5),

    /**
     * 跳转
     */
    VIEW(6),

    /**
     * 默认
     */
    NULL(0);

    private int code;

    private Event(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
