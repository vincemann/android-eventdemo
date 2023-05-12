package com.github.vincemann.eventdemo.common.domain;

import org.greenrobot.eventbus.EventBus;

public abstract class AbstractSubscriber implements AbstractEventBusRegistry.EventBusSubscriber {

    private EventBus mEventBus;


    // todo wo pack ich das rein? Ist das überhaupt nötig?
//    private final ScheduledExecutorService mExecutorService;
//
//    mExecutorService = Executors.newSingleThreadScheduledExecutor();


//    public ScheduledFuture<Object> postDelayed(Object event, long delay) {
//        return mExecutorService.schedule(new EDEventBus.PostEventCallable(this, event), delay, TimeUnit.MILLISECONDS);
//    }
//
//    private class PostEventCallable implements Callable<Object> {
//        private final EDEventBus mEventBus;
//        private final Object mEvent;
//
//        public PostEventCallable(EDEventBus eventBus, Object event) {
//            mEventBus = eventBus;
//            mEvent = event;
//        }
//
//        @Override
//        public Object call() throws Exception {
//            mEventBus.post(mEvent);
//            return null;
//        }
//    }

    @Override
    public final Object register(EventBus eventBus) {
        mEventBus = eventBus;
        mEventBus.register(this);
        return this;
    }

    public final void unregister(EventBus eventBus) {
        eventBus.unregister(this);
        mEventBus = null;
    }

    protected void post(Object event) {
        if (mEventBus == null) {
            throw new NullPointerException("PluginController.register() was not called. Is the controller registered in the EventBusRegistry?");
        }
        mEventBus.post(event);
    }

    protected void postSticky(Object event) {
        if (mEventBus == null) {
            throw new NullPointerException("PluginController.register() was not called. Is the controller registered in the EventBusRegistry?");
        }
        mEventBus.postSticky(event);
    }

    protected <T> T removeStickyEvent(Class<T> eventType) {
        if (mEventBus == null) {
            throw new NullPointerException("PluginController.register() was not called. Is the controller registered in the EventBusRegistry?");
        }
        return mEventBus.removeStickyEvent(eventType);
    }

    protected boolean removeStickyEvent(Object event) {
        if (mEventBus == null) {
            throw new NullPointerException("PluginController.register() was not called. Is the controller registered in the EventBusRegistry?");
        }
        return mEventBus.removeStickyEvent(event);
    }

    protected <T> T getStickyEvent(Class<T> eventType) {
        if (mEventBus == null) {
            throw new NullPointerException("PluginController.register() was not called. Is the controller registered in the EventBusRegistry?");
        }
        return mEventBus.getStickyEvent(eventType);
    }
}