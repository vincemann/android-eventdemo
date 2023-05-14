package com.github.vincemann.eventdemo.login.domain;

import com.github.vincemann.eventdemo.event.bus.LoginEventBus;
import com.github.vincemann.eventdemo.event.subscriber.LoginEventBusSubscriber;
import com.github.vincemann.eventdemo.common.domain.AttachFragmentEvent;
import com.github.vincemann.eventdemo.login.presentation.CorrectLoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CorrectLoginEventHandler implements LoginEventBusSubscriber {

    @Subscribe
    public void onEvent(CorrectLoginEvent event) {
        LoginEventBus.getInstance().post(new AttachFragmentEvent(new CorrectLoginFragment()));
    }
}