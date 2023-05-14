package com.github.vincemann.eventdemo.event.registry;

import android.content.Context;

import com.github.vincemann.eventdemo.common.domain.AbstractEventBusRegistry;
import com.github.vincemann.eventdemo.common.domain.EventBusSubscriber;
import com.github.vincemann.eventdemo.event.bus.LoginEventBus;
import com.github.vincemann.eventdemo.login.domain.CorrectLoginEventHandler;
import com.github.vincemann.eventdemo.login.domain.DoLoginEventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginEventBusRegistry extends AbstractEventBusRegistry<LoginEventBus> {

    protected static LoginEventBusRegistry INSTANCE;

    public LoginEventBusRegistry(Context applicationContext) {
        super(applicationContext);
        INSTANCE = this;
    }

    @Override
    protected LoginEventBus createEventBus() {
        return LoginEventBus.getInstance();
    }


    public static LoginEventBusRegistry getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("No Instance of DefaultEventBusRegistry found. Create a new Instance through your subclass and set this INSTANCE");
        }
        return INSTANCE;
    }

    @Override
    protected List<EventBusSubscriber<LoginEventBus>> createDefaultSubscribers() {
        List<EventBusSubscriber<LoginEventBus>> subscribers = new ArrayList<>();
        subscribers.addAll(Arrays.asList(
                new DoLoginEventHandler(),
                new CorrectLoginEventHandler()
        ));
        return subscribers;
    }
}
