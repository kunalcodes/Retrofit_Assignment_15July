package com.example.list_of_albums;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class AlbumViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvAlbumName;

    public AlbumViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        mTvAlbumName = itemView.findViewById(R.id.tvAlbumName);
    }

    public void setData(ResponseModel responseModel) {
        mTvAlbumName.setText(responseModel.getTitle());
    }
}
