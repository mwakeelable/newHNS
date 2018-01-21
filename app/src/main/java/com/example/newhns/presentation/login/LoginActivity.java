package com.example.newhns.presentation.login;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newhns.R;
import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.model.LoginTokenResponseModel;
import com.example.newhns.presentation.base.BasePresenter;
import com.example.newhns.utils.FactoryInjection;
import com.example.newhns.utils.UIManager;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, View.OnClickListener {
    LoginPresenter loginPresenter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        doInjection();
    }

    private void initViews() {
        TextView text1 = (TextView) findViewById(R.id.label);
        TextView text2 = (TextView) findViewById(R.id.textView);
        TextView text3 = (TextView) findViewById(R.id.textView01);

        final TextView login = (TextView) findViewById(R.id.textView5);
        TextView forget = (TextView) findViewById(R.id.textView4);
        TextView parent = (TextView) findViewById(R.id.textView2);


        final EditText username = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText01);


        text1.setTypeface(UIManager.tf, Typeface.NORMAL);
        text2.setTypeface(UIManager.tf, Typeface.NORMAL);
        text3.setTypeface(UIManager.tf, Typeface.NORMAL);


        login.setTypeface(UIManager.tf, Typeface.NORMAL);
        forget.setTypeface(UIManager.tf, Typeface.NORMAL);
        parent.setTypeface(UIManager.tf, Typeface.NORMAL);


        username.setTypeface(UIManager.tf, Typeface.NORMAL);
        password.setTypeface(UIManager.tf, Typeface.NORMAL);

        progressBar = (ProgressBar) findViewById(R.id.progressn);

        login.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    public void doInjection() {
        loginPresenter = new LoginPresenter
                (FactoryInjection.provideUseCaseHandler(), this, FactoryInjection.injectLoginGetTokenUseCase());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView5:
                //DoLogin
        }
    }

    @Override
    public void getUserTokenSuccess(LoginTokenResponseModel resp) {

    }

    @Override
    public void showErrorMessage(GenerericResponseModel msg) {

    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setLoadingIndicator(boolean showIndicator) {
        if (showIndicator) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.getIndeterminateDrawable()
                    .setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        presenter.start();
    }
}
