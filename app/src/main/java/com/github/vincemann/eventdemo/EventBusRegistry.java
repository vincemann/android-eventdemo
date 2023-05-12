package com.github.vincemann.eventdemo;

import android.content.Context;

import com.github.vincemann.eventdemo.common.domain.AbstractEventBusRegistry;
import com.github.vincemann.eventdemo.login.domain.CorrectLoginEventHandler;
import com.github.vincemann.eventdemo.login.domain.DoLoginEventHandler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventBusRegistry extends AbstractEventBusRegistry {

    protected EventBusRegistry(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    protected List<EventBusSubscriber> createDefaultSubscribers() {
        List<EventBusSubscriber> subscribers = new ArrayList<>();
        subscribers.addAll(Arrays.asList(
                new DoLoginEventHandler(),
                new CorrectLoginEventHandler()
        ));
        return subscribers;
    }
}