package com.example.newhns.data;

import com.example.newhns.data.login.model.LoginTokenResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public interface HnsWebServices {
    @FormUrlEncoded
    @POST("token")
    Call<LoginTokenResponseModel> getUserToken(@Field("username") String username,
                                               @Field("password") String password,
                                               @Field("grant_type") String grant_type);

}
