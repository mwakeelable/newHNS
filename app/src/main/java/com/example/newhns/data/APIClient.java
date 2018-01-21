package com.example.newhns.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class APIClient {
    private static HnsWebServices hnsWebServices;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit==null)
            retrofit = new Retrofit.Builder().baseUrl(URLS.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        else{
            return retrofit;
        }
        return retrofit;
    }

    public static HnsWebServices getHnsWebServices(){
        if(hnsWebServices == null)
            hnsWebServices = getRetrofit().create(HnsWebServices.class);
        else{
            return hnsWebServices;
        }
        return hnsWebServices;
    }

}
