package com.github.vincemann.eventdemo.common.presentation;

import android.app.Activity;

import com.github.vincemann.eventdemo.common.domain.AttachFragmentEvent;
import com.github.vincemann.eventdemo.common.domain.AbstractEventBusRegistry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public abstract class EventReportingActivity extends Activity implements AbstractEventBusRegistry.EventBusSubscriber {

    private Integer containerId;

    public EventReportingActivity(Integer containerId) {
        this.containerId = containerId;
    }

    @Override
    public Object register(EventBus eventBus) {
        eventBus.register(this);
        return this;
    }

    @Override
    public void unregister(EventBus eventBus) {
        eventBus.unregister(this);
    }


    @Override
    protected void onDestroy() {
        // todo use EventRegistries unregister method maybe?
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

//    /**
//     * Overwrite and annotate with @Subscribe and call {@link this#attachFragment(Fragment)}
//     * @param event
//     */
//    public abstract void onEvent(AttachFragmentEvent event);

    @Subscribe
    public void onEvent(AttachFragmentEvent event) {
        attachFragment(event.getFragment());
    }

    protected void attachFragment(android.app.Fragment fragment) {
        getFragmentManager().beginTransaction().replace(containerId, fragment).addToBackStack(null).commit();
    }

}
