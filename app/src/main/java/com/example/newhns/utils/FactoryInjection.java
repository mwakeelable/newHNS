package com.example.newhns.utils;

import com.example.newhns.data.login.LoginRepository;
import com.example.newhns.data.login.remote.LoginGetTokenRemoteDataSource;
import com.example.newhns.domain.LoginGetTokenUseCase;
import com.example.newhns.domain.usecase.UseCaseHandler;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class FactoryInjection {


    public static UseCaseHandler provideUseCaseHandler() {

        return UseCaseHandler.getInstance();
    }

    public static LoginGetTokenRemoteDataSource injectLoginGetTokenDataSource() {
        return LoginGetTokenRemoteDataSource.getInstance();
    }

    public static LoginRepository injectLogRepository() {
        return LoginRepository.getInstance(injectLoginGetTokenDataSource());
    }

    public static LoginGetTokenUseCase injectLoginGetTokenUseCase() {
        return new LoginGetTokenUseCase(injectLogRepository());
    }
}
