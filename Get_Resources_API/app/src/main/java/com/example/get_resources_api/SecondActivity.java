package com.example.get_resources_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    
    private TextView mTvName;
    private TextView mTvYear;
    private TextView mTvColour;
    private ProgressBar progressBar;
    private ConstraintLayout layout;
    private int Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Number = getIntent().getIntExtra("Number", 0);
        initViews();
        callApi();
    }

    private void callApi() {
        progressBar.setVisibility(View.VISIBLE);
        ApiService apiService = Network.getInstance().create(ApiService.class);
        apiService.getApiData(Number).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body() != null){
                    ResponseModel responseModel = response.body();
                    mTvName.setText(responseModel.getData().getName());
                    mTvYear.setText(responseModel.getData().getYear()+"");
                    mTvColour.setText(responseModel.getData().getColor());
                    layout.setBackgroundColor(Color.parseColor(responseModel.getData().getColor()));
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(SecondActivity.this,"Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        mTvName = findViewById(R.id.tvName);
        mTvYear = findViewById(R.id.tvYear);
        mTvColour = findViewById(R.id.tvColour);
        progressBar = findViewById(R.id.progressBar);
        layout = findViewById(R.id.layout);
    }
}