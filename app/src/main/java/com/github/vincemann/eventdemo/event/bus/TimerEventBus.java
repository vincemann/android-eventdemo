package com.github.vincemann.eventdemo.event.bus;

import com.github.vincemann.eventdemo.common.domain.AbstractEventBus;

public class TimerEventBus extends AbstractEventBus {
    private static volatile TimerEventBus defaultInstance;

    public static TimerEventBus getInstance() {
        if (defaultInstance == null) {
            synchronized (TimerEventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new TimerEventBus();
                }
            }
        }
        return defaultInstance;
    }
}
