package com.example.javaapp.Build;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class build_adapter extends RecyclerView.Adapter<build_adapter.MyViewClass> {

    ArrayList<Buildings> buildingsArrayList;
    Context context;

    public build_adapter(ArrayList<Buildings> buildings,
                         Context context) {
        this.buildingsArrayList = buildings;
        this.context = context;
    }
    public void filterList(ArrayList<Buildings> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        buildingsArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.build_row, parent, false);
        MyViewClass myViewClass = new MyViewClass(v);
        return myViewClass;
    }

    @Override
    public void onBindViewHolder(@NonNull build_adapter.MyViewClass holder, int position) {

        final int pos = position;
        holder.title.setText(buildingsArrayList.get(position).getTitle());
        holder.cost.setText(buildingsArrayList.get(position).getCost()  + " ₽ в мес.");
        holder.street.setText(buildingsArrayList.get(position).getStreet());
        holder.metro.setText(buildingsArrayList.get(position).getMetro());
        Picasso.with(context).load(buildingsArrayList.get(position).getImage()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), build_info.class);
                intent.putExtra("title", buildingsArrayList.get(pos).getTitle());
                intent.putExtra("cost", buildingsArrayList.get(pos).getCost());
                intent.putExtra("street", buildingsArrayList.get(pos).getStreet());
                intent.putExtra("metro", buildingsArrayList.get(pos).getMetro());
                intent.putExtra("descr", buildingsArrayList.get(pos).getDescription());
                intent.putExtra("coords1", buildingsArrayList.get(pos).getCoords1());
                intent.putExtra("coords2", buildingsArrayList.get(pos).getCoords2());
                intent.putExtra("image", buildingsArrayList.get(pos).getImage());
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return buildingsArrayList.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView title;
        TextView cost;
        TextView street;
        TextView metro;
        ImageView image;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            cost = itemView.findViewById(R.id.cost);
            street = itemView.findViewById(R.id.street);
            metro = itemView.findViewById(R.id.metro);
            image = itemView.findViewById(R.id.imageBuildrow);

        }
    }
}