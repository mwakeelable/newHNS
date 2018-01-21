package com.example.newhns.data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Wakeel on 21-Jan-18.
 */

public class ErrorUtils {

    public static GenerericResponseModel parseError(Response<?> response) {

        GenerericResponseModel generericResponseModel =null;
        Gson gson = new Gson();
        TypeAdapter<GenerericResponseModel> adapter = gson.getAdapter(GenerericResponseModel.class);
        if (response.errorBody() != null) {
            try {
                generericResponseModel = adapter.fromJson(response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
                return generericResponseModel;

            }

        }

        return generericResponseModel;
    }
}


