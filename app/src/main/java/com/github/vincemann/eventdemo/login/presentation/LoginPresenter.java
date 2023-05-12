package com.github.vincemann.eventdemo.login.presentation;


import com.github.vincemann.eventdemo.login.domain.DoLoginEvent;

import org.greenrobot.eventbus.EventBus;

public class LoginPresenter {
    public void performLogin(String username, String password) {
        EventBus.getDefault().post(new DoLoginEvent(username, password));
    }
}
