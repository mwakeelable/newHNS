package com.example.newhns.presentation.login;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    RelativeLayout btnLogin;
    EditText emailTXT, passwordTXT;

    private void initViews() {
        btnLogin = findViewById(R.id.btnLogin);
        emailTXT = findViewById(R.id.emailTXT);
        passwordTXT = findViewById(R.id.passwordTXT);
        TextView text1 = findViewById(R.id.label);
        TextView text2 = findViewById(R.id.textView);
        TextView text3 = findViewById(R.id.textView01);
        final TextView login = findViewById(R.id.textView5);
        TextView forget = findViewById(R.id.textView4);
        TextView parent = findViewById(R.id.textView2);
        text1.setTypeface(UIManager.tf, Typeface.NORMAL);
        text2.setTypeface(UIManager.tf, Typeface.NORMAL);
        text3.setTypeface(UIManager.tf, Typeface.NORMAL);
        login.setTypeface(UIManager.tf, Typeface.NORMAL);
        forget.setTypeface(UIManager.tf, Typeface.NORMAL);
        parent.setTypeface(UIManager.tf, Typeface.NORMAL);
        emailTXT.setTypeface(UIManager.tf, Typeface.NORMAL);
        passwordTXT.setTypeface(UIManager.tf, Typeface.NORMAL);
        progressBar = (ProgressBar) findViewById(R.id.progressn);
        btnLogin.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    public void doInjection() {
        loginPresenter = new LoginPresenter
                (FactoryInjection.provideUseCaseHandler(), this, FactoryInjection.injectLoginGetTokenUseCase());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                //DoLogin
                if (emailTXT.getEditableText().toString().equals("") || passwordTXT.getEditableText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Missing Data", Toast.LENGTH_SHORT).show();
                } else {
                    String username = emailTXT.getEditableText().toString();
                    String password = passwordTXT.getEditableText().toString();
                    String grantType = "password";

                    loginPresenter.getUserToken(true, username, password, grantType);
                }
        }
    }

    @Override
    public void getUserTokenSuccess(LoginTokenResponseModel resp) {
        Toast.makeText(LoginActivity.this, resp.getAccessToken(), Toast.LENGTH_SHORT);
        Log.d("Token", resp.getAccessToken());
    }

    @Override
    public void showErrorMessage(GenerericResponseModel msg) {
        Toast.makeText(LoginActivity.this, msg.getMessage(), Toast.LENGTH_SHORT);
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
