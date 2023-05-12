package com.github.vincemann.eventdemo.login.domain;

import com.github.vincemann.eventdemo.common.domain.AttachFragmentEvent;
import com.github.vincemann.eventdemo.common.domain.AbstractSubscriber;
import com.github.vincemann.eventdemo.login.presentation.CorrectLoginFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CorrectLoginEventHandler extends AbstractSubscriber {

    @Subscribe
    public void onEvent(CorrectLoginEvent event) {
        EventBus.getDefault().post(new AttachFragmentEvent(new CorrectLoginFragment()));
    }
}