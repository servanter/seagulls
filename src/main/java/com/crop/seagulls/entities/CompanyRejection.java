package com.crop.seagulls.entities;


import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

public class CompanyRejection extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = -9129959231470204830L;

    private Long id;

    private Long authId;

    private Integer type;

    private String opinion;

    private Timestamp auditTime;

    private Timestamp updateTime;

    private Long auditId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auditId == null) ? 0 : auditId.hashCode());
        result = prime * result + ((auditTime == null) ? 0 : auditTime.hashCode());
        result = prime * result + ((authId == null) ? 0 : authId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((opinion == null) ? 0 : opinion.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        CompanyRejection other = (CompanyRejection) obj;
        if (auditId == null) {
            if (other.auditId != null)
                return false;
        } else if (!auditId.equals(other.auditId))
            return false;
        if (auditTime == null) {
            if (other.auditTime != null)
                return false;
        } else if (!auditTime.equals(other.auditTime))
            return false;
        if (authId == null) {
            if (other.authId != null)
                return false;
        } else if (!authId.equals(other.authId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (opinion == null) {
            if (other.opinion != null)
                return false;
        } else if (!opinion.equals(other.opinion))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        return true;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String toString() {
        return "PersonRejection [id=" + id + ", authId=" + authId + ", type=" + type + ", opinion=" + opinion + ", auditTime=" + auditTime + ", updateTime=" + updateTime + ", auditId=" + auditId
                + "]";
    }

}
