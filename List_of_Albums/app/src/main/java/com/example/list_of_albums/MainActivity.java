package com.example.list_of_albums;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ResponseModel> responseModelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        callApi();
    }

    private void callApi() {
        progressBar.setVisibility(View.VISIBLE);
        ApiService apiService = Network.getInstance().create(ApiService.class);
        apiService.getListOfAlbums().enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body() != null){
                    responseModelList= response.body();
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


    private void setAdapter() {
        albumAdapter = new AlbumAdapter(responseModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }
}