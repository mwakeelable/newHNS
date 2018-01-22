package com.example.newhns.presentation.login;

import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.model.LoginTokenResponseModel;
import com.example.newhns.domain.LoginGetTokenUseCase;
import com.example.newhns.domain.usecase.UseCase;
import com.example.newhns.domain.usecase.UseCaseHandler;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class LoginPresenter implements LoginContract.LoginPresenter {
    private UseCaseHandler useCaseHandler;
    private LoginContract.LoginView logView;
    private LoginGetTokenUseCase loginGetTokenUseCase;

    public LoginPresenter(UseCaseHandler useCaseHandler, LoginContract.LoginView logView, LoginGetTokenUseCase loginGetTokenUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.logView = logView;
        this.loginGetTokenUseCase = loginGetTokenUseCase;
        logView.setPresenter(this);
    }

    @Override
    public void getUserToken(Boolean showLoadingUI, String username, String password, String grantType) {
        if (showLoadingUI && logView.isActive()) {
            logView.setLoadingIndicator(true);
        }
        LoginGetTokenUseCase.LoginGetTokenRequestValues requestValues = new LoginGetTokenUseCase.LoginGetTokenRequestValues(username, password, grantType);
        useCaseHandler.execute(loginGetTokenUseCase, requestValues, new UseCase.UseCaseCallback<LoginGetTokenUseCase.LoginGetTokenResponseValue>() {
            @Override
            public void onSuccess(LoginGetTokenUseCase.LoginGetTokenResponseValue response) {
                logView.setLoadingIndicator(false);
                LoginTokenResponseModel responseModel = response.getResponseModel();
                if (responseModel.getAccessToken() != null) {
                    logView.getUserTokenSuccess(responseModel);
                }
            }

            @Override
            public void onError(LoginGetTokenUseCase.LoginGetTokenResponseValue response) {
                GenerericResponseModel generericResponseModel = response.getGenerericResponseModel();
                logView.showErrorMessage(generericResponseModel);
                logView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void start() {

    }
}
