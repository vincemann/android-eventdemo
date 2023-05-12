package com.github.vincemann.eventdemo;

import android.app.Application;

public class App extends Application {
    private static App INSTANCE;
    private EventBusRegistry registry;

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
        registry = new EventBusRegistry(this);
        registry.registerDefaultSubscribers();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        INSTANCE = null;
        registry.unregisterAllSubscribers();
        registry = null;
    }
}
