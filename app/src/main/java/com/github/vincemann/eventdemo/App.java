package com.github.vincemann.eventdemo;

import android.app.Application;

import com.github.vincemann.eventdemo.event.registry.LoginEventBusRegistry;
import com.github.vincemann.eventdemo.event.registry.TimerEventBusRegistry;

public class App extends Application {
    private static App INSTANCE;
    private LoginEventBusRegistry loginBusRegistry;
    private TimerEventBusRegistry timerEventBusRegistry;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
        startEventProcessing();
    }

    private void startEventProcessing() {
        loginBusRegistry = new LoginEventBusRegistry(this);
        timerEventBusRegistry = new TimerEventBusRegistry(this);
        loginBusRegistry.registerDefaultSubscribers();
        timerEventBusRegistry.registerDefaultSubscribers();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        INSTANCE = null;
        loginBusRegistry.unregisterAllSubscribers();
        timerEventBusRegistry.unregisterAllSubscribers();
        loginBusRegistry = null;
        timerEventBusRegistry = null;
    }
}
