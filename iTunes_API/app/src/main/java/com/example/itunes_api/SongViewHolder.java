package com.example.itunes_api;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

public class SongViewHolder extends RecyclerView.ViewHolder {

    private ImageView mIvImage;
    private TextView mTvSong;
    private TextView mTvArtist;
    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnDelete;
    private ItemClickListener itemClickListener;

    public SongViewHolder(@NonNull @NotNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initVies(itemView);
    }

    private void initVies(View itemView) {
        mIvImage = itemView.findViewById(R.id.ivImage);
        mTvSong = itemView.findViewById(R.id.tvSong);
        mTvArtist = itemView.findViewById(R.id.tvArtist);
        mBtnPlay = itemView.findViewById(R.id.btnPlay);
        mBtnPause = itemView.findViewById(R.id.btnPuase);
        mBtnDelete = itemView.findViewById(R.id.btnDelete);
    }

    public void setData(ResultsModel resultsModel) {
        mTvSong.setText(resultsModel.getTrackName());
        mTvArtist.setText(resultsModel.getArtistName());
        Glide.with(mIvImage).load(Uri.parse(resultsModel.getArtworkUrl100())).into(mIvImage);
        String songUrl = resultsModel.getPreviewUrl();

        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(getAdapterPosition(), songUrl, 1);
            }
        });

        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(getAdapterPosition(), songUrl, 2);
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(getAdapterPosition(), songUrl, 3);
            }
        });
    }
}
