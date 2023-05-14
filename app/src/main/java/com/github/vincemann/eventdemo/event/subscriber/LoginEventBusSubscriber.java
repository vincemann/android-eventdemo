package com.github.vincemann.eventdemo.event.subscriber;

import com.github.vincemann.eventdemo.common.domain.EventBusSubscriber;
import com.github.vincemann.eventdemo.event.bus.LoginEventBus;

public interface LoginEventBusSubscriber extends EventBusSubscriber<LoginEventBus> {
}
