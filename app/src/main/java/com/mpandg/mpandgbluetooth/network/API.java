package com.mpandg.mpandgbluetooth.network;

import com.mpandg.mpandgbluetooth.model.Error;
import com.mpandg.mpandgbluetooth.model.RegisterResult;
import com.mpandg.mpandgbluetooth.model.Result;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;

/**
 * Created by Ali Kabiri on 4/19/2016 .
 * You can find me here: kabiri.org
 * or: ali@kabiri.org
 * please make sure you're in happy state
 * before contacting me.
 */
public interface API {
    String URL = "http://hooshnoosh.mpandg.com:2514";

    @POST("/register/new/")
    void getCities(
            @Part("username") String password,
            @Part("userpass") String username,
            Callback<Result<RegisterResult>> result
    );

    @GET("/register/validate/")
    void validateRegistration(
            @Header("Authorization") String token,
            Callback<Result<Error>> result
    );
}
