package com.github.vincemann.eventdemo.login.domain;

import com.github.vincemann.eventdemo.common.domain.AbstractSubscriber;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DoLoginEventHandler extends AbstractSubscriber {

    @Subscribe
    public void onEvent(DoLoginEvent event) {
        /**
         * Here is where we should perform a HTTP request to check the login credentials. Let's
         * assume the login is right
         */
        EventBus.getDefault().post(new CorrectLoginEvent());
    }
}