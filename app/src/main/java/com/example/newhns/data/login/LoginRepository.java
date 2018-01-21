package com.example.newhns.data.login;

import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.model.LoginTokenResponseModel;
import com.example.newhns.data.login.remote.LoginGetTokenRemoteDataSource;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class LoginRepository implements LoginDataSource {
    LoginDataSource mRemoteDataSource ;
    private  static LoginRepository INSTANCE ;

    private LoginRepository(LoginDataSource mRemoteDataSource){

        this.mRemoteDataSource = mRemoteDataSource ;
    }

    public static LoginRepository getInstance(LoginGetTokenRemoteDataSource loginGetTokenRemoteDataSource ) {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository(loginGetTokenRemoteDataSource);
        }
        else{return INSTANCE;}
        return INSTANCE;
    }

    @Override
    public void getUserToken(String username, String password, String grantType, final GetTokenCallBack callBack) {
        mRemoteDataSource.getUserToken(username, password, grantType, new GetTokenCallBack() {
            @Override
            public void getTokenSuccess(LoginTokenResponseModel model) {
                callBack.getTokenSuccess(model);
            }

            @Override
            public void getTokenFailed(GenerericResponseModel errMsg) {
                callBack.getTokenFailed(errMsg);
            }
        });
    }
}
