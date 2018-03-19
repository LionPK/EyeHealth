package com.crud.singl.eyehealth.service;

import com.crud.singl.eyehealth.entities.Account_en;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by singl on 3/20/2018.
 */

public interface AccountService {

    @POST("create")
    Call<Boolean> create(@Body Account_en accountEn);

    @POST("login")
    Call<Boolean> login(@Body Account_en accountEn);

    @PUT("update")
    Call<Boolean> update(@Body Account_en accountEn);

    @GET("find/{email}")
    Call<Account_en> find(@Path("email") String email);
}
