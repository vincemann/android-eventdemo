package com.github.vincemann.eventdemo.common.domain;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractEventBusRegistry {

    protected static AbstractEventBusRegistry INSTANCE;

    protected final EventBus eventBus = EventBus.getDefault();
    protected final List<EventBusSubscriber> defaultEventSubscribers = new ArrayList<>();
    protected final HashMap<Object, EventBusSubscriber> eventSubscribers = new HashMap<>();
    protected final Context applicationContext;

    protected AbstractEventBusRegistry(Context applicationContext) {
        this.applicationContext = applicationContext;
        INSTANCE = this;
    }

    public static interface EventBusSubscriber {
        Object register(EventBus eventBus);
        void unregister(EventBus eventBus);
    }

    public static void setInstance(AbstractEventBusRegistry instance) {
        INSTANCE = instance;
    }

    public static AbstractEventBusRegistry getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("No Instance of SxEventBusRegistry found. Create a new Instance through your subclass and set this INSTANCE");
        }
        return INSTANCE;
    }

    public void registerDefaultSubscribers() {
        onBeforeRegisterDefaultSubscribers();
        defaultEventSubscribers.clear();
        defaultEventSubscribers.addAll(createDefaultSubscribers());
        for (EventBusSubscriber subscriber : defaultEventSubscribers) {
            registerSubscriber(subscriber);
        }
    }

    public void unregisterAllSubscribers() {
        onBeforeUnregisterAllEventSubscribers();
        for (Object subscriber : eventSubscribers.keySet()) {
            eventBus.unregister(subscriber);
        }
        eventSubscribers.clear();
    }

    public void registerSubscriber(EventBusSubscriber subscriber) {
        if (eventSubscribers.containsValue(subscriber)) {
            return;
        }

        Object registeredSubscriber = subscriber.register(eventBus);
        eventSubscribers.put(registeredSubscriber, subscriber);
    }

    public void unregisterSubscriber(Object subscriber) {
        if (!eventSubscribers.containsKey(subscriber)) {
            return;
        }

        EventBusSubscriber visitor = eventSubscribers.get(subscriber);
        visitor.unregister(eventBus);
        eventSubscribers.remove(subscriber);
    }

    protected abstract List<EventBusSubscriber> createDefaultSubscribers();
    protected void onBeforeRegisterDefaultSubscribers(){}
    protected void onBeforeUnregisterAllEventSubscribers(){}
}
