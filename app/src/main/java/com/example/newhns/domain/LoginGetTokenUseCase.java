package com.example.newhns.domain;

import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.LoginDataSource;
import com.example.newhns.data.login.LoginRepository;
import com.example.newhns.data.login.model.LoginTokenResponseModel;
import com.example.newhns.domain.usecase.UseCase;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class LoginGetTokenUseCase extends UseCase<LoginGetTokenUseCase.LoginGetTokenRequestValues, LoginGetTokenUseCase.LoginGetTokenResponseValue>{
    LoginRepository mLoginRepository;

    public LoginGetTokenUseCase(LoginRepository mLoginRepository) {
        this.mLoginRepository = mLoginRepository;
    }


    @Override
    protected void executeUseCase(LoginGetTokenRequestValues requestValues) {
        mLoginRepository.getUserToken(requestValues.username, requestValues.password, requestValues.grantType, new LoginDataSource.GetTokenCallBack() {
            @Override
            public void getTokenSuccess(LoginTokenResponseModel model) {
                LoginGetTokenResponseValue loginGetTokenResponseValue = new LoginGetTokenResponseValue(model);
                getUseCaseCallback().onSuccess(loginGetTokenResponseValue);
            }

            @Override
            public void getTokenFailed(GenerericResponseModel errMsg) {
                LoginGetTokenResponseValue generericResponseModel = new LoginGetTokenResponseValue(errMsg);
                getUseCaseCallback().onError(generericResponseModel);
            }
        });
    }

    public static final class LoginGetTokenRequestValues implements UseCase.RequestValues {

        String username, password, grantType;

        public LoginGetTokenRequestValues(String username, String password, String grantType) {
            this.username = username;
            this.password = password;
            this.grantType = grantType;
        }
    }

    public static final class LoginGetTokenResponseValue implements UseCase.ResponseValue {

        LoginTokenResponseModel model;
        GenerericResponseModel generericResponseModel;

        public LoginGetTokenResponseValue(LoginTokenResponseModel model) {
            this.model = model;
        }

        public LoginGetTokenResponseValue(GenerericResponseModel generericResponseModel) {
            this.generericResponseModel = generericResponseModel;

        }

        public LoginTokenResponseModel getResponseModel(){
            return model;
        }

        public GenerericResponseModel getGenerericResponseModel(){
            return generericResponseModel;
        }
    }
}
