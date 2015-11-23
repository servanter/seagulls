package com.crop.seagulls.bean;

import com.crop.seagulls.entities.User;

public class Base extends Page {

    private User loginUser;

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

}
