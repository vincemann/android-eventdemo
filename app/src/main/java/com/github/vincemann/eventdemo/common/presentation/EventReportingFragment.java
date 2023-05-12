package com.github.vincemann.eventdemo.common.presentation;

import android.app.Fragment;

import org.greenrobot.eventbus.EventBus;

public class EventReportingFragment extends Fragment {

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
