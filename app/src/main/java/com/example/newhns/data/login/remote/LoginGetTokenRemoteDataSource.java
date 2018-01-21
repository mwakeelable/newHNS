package com.example.newhns.data.login.remote;

import com.example.newhns.data.APIClient;
import com.example.newhns.data.ErrorUtils;
import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.LoginDataSource;
import com.example.newhns.data.login.model.LoginTokenResponseModel;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class LoginGetTokenRemoteDataSource implements LoginDataSource {
    private static LoginGetTokenRemoteDataSource instance;

    public static LoginGetTokenRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new LoginGetTokenRemoteDataSource();
        } else {
            return instance;
        }
        return instance;

    }

    @Override
    public void getUserToken(String username, String password, String grantType, GetTokenCallBack callBack) {
        try {
            Response<LoginTokenResponseModel> response = APIClient.getHnsWebServices().getUserToken(username, password, grantType).execute();
            if (response.isSuccessful()) {
                LoginTokenResponseModel responseModel = response.body();
                callBack.getTokenSuccess(responseModel);

            } else {
                GenerericResponseModel ge = new GenerericResponseModel();
                ge.setMessage(ErrorUtils.parseError(response).getMessage());
                callBack.getTokenFailed(ge);

            }
        } catch (IOException e) {
            GenerericResponseModel generericResponseModel = new GenerericResponseModel();
            generericResponseModel.setMessage(e.getMessage());
            callBack.getTokenFailed(generericResponseModel);
            e.printStackTrace();
        }
    }
}
