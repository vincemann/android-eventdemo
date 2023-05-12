package com.github.vincemann.eventdemo.timer.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gunhansancar.eventbusexample.R;
import com.github.vincemann.eventdemo.common.domain.AttachFragmentEvent;
import com.github.vincemann.eventdemo.common.presentation.EventReportingFragment;
import com.github.vincemann.eventdemo.login.presentation.LoginFragment;
import com.github.vincemann.eventdemo.timer.domain.AddTimerElementEvent;
import com.github.vincemann.eventdemo.timer.domain.StopTimerEvent;
import com.github.vincemann.eventdemo.timer.domain.TimerService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimerFragment extends EventReportingFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    TimerRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_fragment, container, false);
        ButterKnife.bind(this, view);
//        presenter = new LoginPresenter();



        adapter = new TimerRecyclerAdapter();
        adapter.setOnItemClickListener(new TimerRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TimerRecyclerAdapter.ItemHolder item, int position) {
                EventBus.getDefault().post(new AttachFragmentEvent(new LoginFragment()));
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }

    @OnClick(R.id.startButton)
    public void onStartClicked() {
        // todo call presenter method which triggers event and calls view.displayTimerStarted().
        //  EventHandler is registered by registry and proceeds by handling
        //  event and maybe trigger more events
        Toast.makeText(getActivity(), "Timer is started.", Toast.LENGTH_SHORT).show();
        getActivity().startService(new Intent(getActivity(), TimerService.class));
    }

    @OnClick(R.id.stopButton)
    public void onStopClicked() {
        Toast.makeText(getActivity(), "Timer is stopped.", Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new StopTimerEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(AddTimerElementEvent event) {
        adapter.append(event);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback
            = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }


        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            adapter.delete(viewHolder.getAdapterPosition());
        }
    };
}
