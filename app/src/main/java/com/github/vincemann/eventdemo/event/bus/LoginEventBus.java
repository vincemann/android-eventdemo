package com.github.vincemann.eventdemo.event.bus;

import com.github.vincemann.eventdemo.common.domain.AbstractEventBus;

public class LoginEventBus extends AbstractEventBus {
    private static volatile LoginEventBus defaultInstance;

    public static LoginEventBus getInstance() {
        if (defaultInstance == null) {
            synchronized (LoginEventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new LoginEventBus();
                }
            }
        }
        return defaultInstance;
    }
}
