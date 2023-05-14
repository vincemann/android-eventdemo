package com.github.vincemann.eventdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.vincemann.eventdemo.common.domain.AttachFragmentEvent;
import com.github.vincemann.eventdemo.event.registry.TimerEventBusRegistry;
import com.github.vincemann.eventdemo.event.subscriber.GlobalEventBusSubscriber;
import com.github.vincemann.eventdemo.event.registry.LoginEventBusRegistry;
import com.github.vincemann.eventdemo.timer.presentation.TimerFragment;
import com.gunhansancar.eventbusexample.R;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity
//        implements LoginEventBusSubscriber, DefaultEventBusSubscriber
    implements GlobalEventBusSubscriber
{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        LoginEventBusRegistry.getInstance().registerSubscriber(this);
        TimerEventBusRegistry.getInstance().registerSubscriber(this);

        if (savedInstanceState == null) {
            attachFragment(new TimerFragment());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginEventBusRegistry.getInstance().unregisterSubscriber(this);
        TimerEventBusRegistry.getInstance().unregisterSubscriber(this);
    }

    @Subscribe
    public void onEvent(AttachFragmentEvent event) {
        attachFragment(event.getFragment());
    }

    protected void attachFragment(android.app.Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }


}
