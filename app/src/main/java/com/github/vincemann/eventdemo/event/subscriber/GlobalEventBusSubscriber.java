package com.github.vincemann.eventdemo.event.subscriber;

import com.github.vincemann.eventdemo.common.domain.EventBusSubscriber;

import org.greenrobot.eventbus.EventBus;

public interface GlobalEventBusSubscriber extends EventBusSubscriber<EventBus> {
}
