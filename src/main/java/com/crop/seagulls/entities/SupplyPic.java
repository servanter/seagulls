package com.crop.seagulls.entities;

import java.sql.Timestamp;
import java.util.Date;

import com.crop.seagulls.bean.Page;

/**
 * Supply pics
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class SupplyPic extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = 2195856956118752513L;

    private Long id;

    private Long supplyId;

    private String imgUrl;

    private Boolean isFirst;

    private Boolean isValid;

    private Date createTime;

    private Timestamp updateTime;

    private Long operatorId;

    public SupplyPic() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SupplyPic(Long id, Long supplyId, String imgUrl, Boolean isFirst, Boolean isValid, Date createTime, Timestamp updateTime, Long operatorId) {
        super();
        this.id = id;
        this.supplyId = supplyId;
        this.imgUrl = imgUrl;
        this.isFirst = isFirst;
        this.isValid = isValid;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.operatorId = operatorId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((imgUrl == null) ? 0 : imgUrl.hashCode());
        result = prime * result + ((isFirst == null) ? 0 : isFirst.hashCode());
        result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
        result = prime * result + ((operatorId == null) ? 0 : operatorId.hashCode());
        result = prime * result + ((supplyId == null) ? 0 : supplyId.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SupplyPic other = (SupplyPic) obj;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (imgUrl == null) {
            if (other.imgUrl != null)
                return false;
        } else if (!imgUrl.equals(other.imgUrl))
            return false;
        if (isFirst == null) {
            if (other.isFirst != null)
                return false;
        } else if (!isFirst.equals(other.isFirst))
            return false;
        if (isValid == null) {
            if (other.isValid != null)
                return false;
        } else if (!isValid.equals(other.isValid))
            return false;
        if (operatorId == null) {
            if (other.operatorId != null)
                return false;
        } else if (!operatorId.equals(other.operatorId))
            return false;
        if (supplyId == null) {
            if (other.supplyId != null)
                return false;
        } else if (!supplyId.equals(other.supplyId))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        return true;
    }

}
