package com.example.itunes_api;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search")
    Call<ResponseModel> getSong(@Query("term") String songName);

}
