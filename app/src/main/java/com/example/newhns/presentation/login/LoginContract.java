package com.example.newhns.presentation.login;

import com.example.newhns.data.GenerericResponseModel;
import com.example.newhns.data.login.model.LoginTokenResponseModel;
import com.example.newhns.presentation.base.BasePresenter;
import com.example.newhns.presentation.base.BaseView;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public interface LoginContract {
    interface LoginPresenter extends BasePresenter {
        void getUserToken(Boolean showLoadingUI, String username, String password, String grantType);
    }

    interface LoginView extends BaseView {
        void getUserTokenSuccess(LoginTokenResponseModel resp);

        void showErrorMessage(GenerericResponseModel msg);

        boolean isActive();

        void setLoadingIndicator(boolean b);
    }
}
