package com.example.javaapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


//    ArrayList<search_building.Building> buildings;
    ArrayList<String> buildings;
//    ArrayList<search_building.Building> buildings = new ArrayList<search_building.Building>();

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

//    public MainAdapter(ArrayList<search_building.Building> building){
//        buildings = building;
//    }

    public MainAdapter(ArrayList<String> building){
        buildings = building;
    }






    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.Title.setText(buildings.get(position));
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
        }
    }
}
