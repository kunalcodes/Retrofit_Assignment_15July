package com.example.itunes_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongViewHolder> {

    private List<ResultsModel> resultsModelList;
    private ItemClickListener itemClickListener;

    public SongAdapter(List<ResultsModel> resultsModelList, ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
        this.resultsModelList = resultsModelList;
    }

    @NonNull
    @NotNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new SongViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SongViewHolder holder, int position) {
        ResultsModel resultsModel = resultsModelList.get(position);
        holder.setData(resultsModel);
    }

    @Override
    public int getItemCount() {
        return resultsModelList.size();
    }
}
