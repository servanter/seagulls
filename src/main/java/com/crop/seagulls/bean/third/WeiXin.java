package com.crop.seagulls.bean.third;

import com.crop.seagulls.bean.Src;
import com.crop.seagulls.entities.Third;

public class WeiXin extends Third {

    /**
     * 
     */
    private static final long serialVersionUID = -7111861059927745986L;

    public WeiXin() {
        this.src = Src.WEIXIN.getType();
    }

    public void setOpenId(String openId) {
        this.thirdUnionId = openId;
    }

    public void setUnionId(String unionId) {
        this.metaIndex1 = unionId;
    }

    public void setAccessToken(String accessToken) {
        this.metaIndex2 = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.metaIndex3 = refreshToken;
    }

    public void setExpireIn(String expireIn) {
        this.metaIndex4 = expireIn;
    }

    @Override
    public String toString() {
        return "WeiXin [getMetaIndex1()=" + getMetaIndex1() + ", getMetaIndex2()=" + getMetaIndex2() + ", getMetaIndex3()=" + getMetaIndex3() + ", getMetaIndex4()=" + getMetaIndex4()
                + ", getMetaIndex5()=" + getMetaIndex5() + "]";
    }

}
