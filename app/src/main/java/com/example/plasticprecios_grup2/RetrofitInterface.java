package com.example.plasticprecios_grup2;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
        Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/register")
        Call<Void> executeSignUp(@Body HashMap<String, String> map);

    @GET("/mostrarProductosApp")
    Call<List<Products>> getProducts();
}
