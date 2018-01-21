package com.example.newhns.data.login;

import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.model.LoginTokenResponseModel;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public interface LoginDataSource {
    interface GetTokenCallBack {
        public void getTokenSuccess(LoginTokenResponseModel model);

        public void getTokenFailed(GenerericResponseModel errMsg);
    }

    void getUserToken(String username, String password, String grantType, GetTokenCallBack callBack);
}
