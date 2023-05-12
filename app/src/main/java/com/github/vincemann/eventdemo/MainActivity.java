package com.github.vincemann.eventdemo;

import android.os.Bundle;

import com.github.vincemann.eventdemo.common.domain.AttachFragmentEvent;
import com.github.vincemann.eventdemo.common.presentation.EventReportingActivity;
import com.github.vincemann.eventdemo.timer.presentation.TimerFragment;
import com.gunhansancar.eventbusexample.R;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends EventReportingActivity {

    public MainActivity() {
        super(R.id.container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        EventBusRegistry.getInstance().registerSubscriber(this);

        if (savedInstanceState == null) {
            attachFragment(new TimerFragment());
        }
    }


    @Subscribe
    @Override
    public void onEvent(AttachFragmentEvent event) {
        attachFragment(event.getFragment());
    }
}
