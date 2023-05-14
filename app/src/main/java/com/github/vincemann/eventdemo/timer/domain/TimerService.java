package com.github.vincemann.eventdemo.timer.domain;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.github.vincemann.eventdemo.event.bus.TimerEventBus;
import com.github.vincemann.eventdemo.event.registry.TimerEventBusRegistry;
import com.github.vincemann.eventdemo.event.subscriber.TimerEventBusSubscriber;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gunhansancar on 06/04/16.
 */
public class TimerService extends Service implements TimerEventBusSubscriber {

    private Timer timer;
    private AtomicInteger counter = new AtomicInteger();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTimer();
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TimerEventBusRegistry.getInstance().registerSubscriber(this);

    }

    @Override
    public void onDestroy() {
        TimerEventBusRegistry.getInstance().unregisterSubscriber(this);
        stopTimer();
        super.onDestroy();
    }

    @Subscribe
    public void onEvent(StopTimerEvent event) {
        stopTimer();
        stopSelf();
    }

    private void startTimer() {
        stopTimer();
        timer = new Timer();
        timer.scheduleAtFixedRate(new SimpleTimerTask(counter), 0, 3000);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private static class SimpleTimerTask extends TimerTask {

        private AtomicInteger current;

        public SimpleTimerTask(AtomicInteger current) {
            this.current = current;
        }

        @Override
        public void run() {
            TimerEventBus.getInstance().postSticky(new AddTimerElementEvent(current.addAndGet(1)));
        }
    }
}
