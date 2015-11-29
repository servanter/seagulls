package com.crop.seagulls.entities;

import java.sql.Timestamp;

import com.crop.seagulls.bean.Page;

public class Friend extends Page {

    /**
     * 
     */
    private static final long serialVersionUID = 8402717457407022386L;

    private Long id;

    private Long userId;

    private Long friendId;

    private Timestamp createTime;

    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((friendId == null) ? 0 : friendId.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isValid == null) ? 0 : isValid.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        final Friend other = (Friend) obj;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (friendId == null) {
            if (other.friendId != null)
                return false;
        } else if (!friendId.equals(other.friendId))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isValid == null) {
            if (other.isValid != null)
                return false;
        } else if (!isValid.equals(other.isValid))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}
