package com.github.vincemann.eventdemo.login.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gunhansancar.eventbusexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.editTextUsername)
    TextView editTextUsername;

    @BindView(R.id.editTextPassword)
    TextView editTextPassword;

    @BindView(R.id.buttonLogin)
    Button buttonLogin;

    private LoginPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, view);
        presenter = new LoginPresenter();
        return view;
    }

    @OnClick(R.id.buttonLogin)
    public void onLoginClicked() {
        presenter.performLogin(editTextUsername.getText().toString(),editTextPassword.getText().toString());
    }

}
