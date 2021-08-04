package com.example.get_resources_api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/unknown/{userId}")
    Call<ResponseModel> getApiData(@Path("userId") int userId);
}
