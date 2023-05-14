package com.github.vincemann.eventdemo.common.domain;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Create singleton getInstance() method in Subclasses.
 */
public abstract class AbstractEventBus extends EventBus {

    private final ScheduledExecutorService mExecutorService;

    protected AbstractEventBus() {
        super();
        mExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

//    public static void postEvent(Object event) {
//        getInstance().post(event);
//    }
//
//    public static void postStickyEvent(Object event) {
//        getInstance().postSticky(event);
//    }
//
//    public static <T> T getSticky(Class<T> eventType) {
//        return getInstance().getStickyEvent(eventType);
//    }

    public ScheduledFuture<Object> postDelayed(Object event, long delay) {
        return mExecutorService.schedule(new PostEventCallable(this, event), delay, TimeUnit.MILLISECONDS);
    }

    private static class PostEventCallable implements Callable<Object> {
        private final AbstractEventBus mEventBus;
        private final Object mEvent;

        public PostEventCallable(AbstractEventBus eventBus, Object event) {
            mEventBus = eventBus;
            mEvent = event;
        }

        @Override
        public Object call() throws Exception {
            mEventBus.post(mEvent);
            return null;
        }
    }
}
