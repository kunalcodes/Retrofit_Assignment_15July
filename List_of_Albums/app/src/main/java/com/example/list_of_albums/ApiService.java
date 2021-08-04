package com.example.list_of_albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("albums")
    Call<List<ResponseModel>> getListOfAlbums();
}
