package com.github.vincemann.eventdemo.login.domain;

import com.github.vincemann.eventdemo.event.bus.LoginEventBus;
import com.github.vincemann.eventdemo.event.subscriber.LoginEventBusSubscriber;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DoLoginEventHandler implements LoginEventBusSubscriber {

    @Subscribe
    public void onEvent(DoLoginEvent event) {
        /**
         * Here is where we should perform a HTTP request to check the login credentials. Let's
         * assume the login is right
         */
        LoginEventBus.getInstance().post(new CorrectLoginEvent());
    }
}