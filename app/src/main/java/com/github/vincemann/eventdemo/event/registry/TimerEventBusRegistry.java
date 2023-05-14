package com.github.vincemann.eventdemo.event.registry;

import android.content.Context;

import com.github.vincemann.eventdemo.common.domain.AbstractEventBusRegistry;
import com.github.vincemann.eventdemo.common.domain.EventBusSubscriber;
import com.github.vincemann.eventdemo.event.bus.TimerEventBus;

import java.util.ArrayList;
import java.util.List;

public class TimerEventBusRegistry extends AbstractEventBusRegistry<TimerEventBus> {
    protected static TimerEventBusRegistry INSTANCE;

    public TimerEventBusRegistry(Context applicationContext) {
        super(applicationContext);
        INSTANCE = this;
    }

    @Override
    protected TimerEventBus createEventBus() {
        return TimerEventBus.getInstance();
    }

    public static TimerEventBusRegistry getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("No Instance of DefaultEventBusRegistry found. Create a new Instance through your subclass and set this INSTANCE");
        }
        return INSTANCE;
    }

    @Override
    protected List<EventBusSubscriber<TimerEventBus>> createDefaultSubscribers() {
        return new ArrayList<>();
    }
}
