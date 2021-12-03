package com.myfistapp.sunshine_app.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.myfistapp.sunshine_app.Class.CategoryDomain;
import com.myfistapp.sunshine_app.Model.Chitiethoadon;
import com.myfistapp.sunshine_app.Model.Hoadon;
import com.myfistapp.sunshine_app.Model.SanPhamDomain;
import com.myfistapp.sunshine_app.Model.Khachhang;
import com.myfistapp.sunshine_app.Model.Sanphamyeuthich;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("sanpham/list")
    Call<ArrayList<SanPhamDomain>> showitem();

    @GET("sanpham/listdm/{iddm}")
    Call<ArrayList<SanPhamDomain>> showitemcat(@Path("iddm")int iddm);

    @POST("khachhang/add")
    Call<Khachhang> createUser(@Body Khachhang khachHang);

    @GET("khachhang/list")
    Call<ArrayList<Khachhang>> showuser();

    @GET("danhmuc/list")
    Call<ArrayList<CategoryDomain>> showcat();

    @POST("hoadon/add")
    Call<Hoadon> createOrder(@Body Hoadon hoadon);

    @GET("hoadon/listnewest")
    Call<ArrayList<Hoadon>> showlistnewest();

    @POST("chitiethoadon/add")
    Call<Chitiethoadon> createOrderDetail(@Body Chitiethoadon chitiethoadon);

    @GET("sanphamyeuthich/list/{idkh}")
    Call<ArrayList<Sanphamyeuthich>> showFavorite(@Path("idkh")int idkh);

    @POST("sanphamyeuthich/add")
    Call<Sanphamyeuthich> addFavorite(@Body Sanphamyeuthich sanphamyeuthich);

    @POST("sanphamyeuthich/delete/{idkh}/{idsp}")
    Call<Sanphamyeuthich> deleteFavorite(@Path("idkh")int idkh, @Path("idsp")int idsp);

    @POST("khachhang/update/{id}")
    Call<Khachhang> updateKhachhang(@Body Khachhang khachhang,@Path("id") int id);

}
