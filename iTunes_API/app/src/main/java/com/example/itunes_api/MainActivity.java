package com.example.itunes_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private Button mBtnSearch;
    private RecyclerView recyclerView;
    private EditText mEtSearch;
    private List<ResultsModel> resultsModelList;
    private SongAdapter songAdapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi();
            }
        });
    }

    private void callApi() {
        String songName = mEtSearch.getText().toString().trim();
        ApiService apiService = Network.getInstance().create(ApiService.class);
        apiService.getSong(songName).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null){
                    ResponseModel responseModel = response.body();
                    resultsModelList = (responseModel.getResults());
                    setAdapter();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    private void setAdapter() {
        songAdapter = new SongAdapter(resultsModelList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setAdapter(songAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void initViews() {
            mBtnSearch = findViewById(R.id.btnSearch);
            mEtSearch = findViewById(R.id.etSearch);
            recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void onItemClicked(int position, String url, int button) {

        switch (button) {
            case 1:
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(this, Uri.parse(url));
                }
                mediaPlayer.start();
                break;
            case 2:
                mediaPlayer.pause();
                mediaPlayer.release();
                mediaPlayer = null;
                break;
            case 3:
                resultsModelList.remove(position);
                songAdapter.notifyDataSetChanged();
                break;
        }
    }
}