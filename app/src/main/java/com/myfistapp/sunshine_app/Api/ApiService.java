package com.myfistapp.sunshine_app.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myfistapp.sunshine_app.Class.SanPhamDomain;
import com.myfistapp.sunshine_app.Model.KhachHang;
import com.myfistapp.sunshine_app.Model.Sanpham;

import java.util.ArrayList;
import java.util.Currency;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.9:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("sanpham/list")
    Call<ArrayList<SanPhamDomain>> showitem();

    @POST("khachhang/add")
    Call<KhachHang> createUser(@Body KhachHang khachHang);

}
