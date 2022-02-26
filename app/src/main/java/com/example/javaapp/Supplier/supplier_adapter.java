package com.example.javaapp.Supplier;

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

public class supplier_adapter extends RecyclerView.Adapter<supplier_adapter.MyViewClass> {
    ArrayList<Supplier> supplierArrayList;
    Context context;

    public supplier_adapter(ArrayList<Supplier> suppliers,
                         Context context) {
        this.supplierArrayList = suppliers;
        this.context = context;
    }
    public void filterList(ArrayList<Supplier> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        supplierArrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public supplier_adapter.MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_row, parent, false);
        supplier_adapter.MyViewClass myViewClass = new supplier_adapter.MyViewClass(v);
        return myViewClass;
    }



    @Override
    public void onBindViewHolder(@NonNull supplier_adapter.MyViewClass holder, int position) {

        final int pos = position;
        holder.titleSupplierRow.setText(supplierArrayList.get(position).getTitle());
        holder.ratingRowSupplier.setText(stringRating(supplierArrayList.get(position).getRating()));
        holder.costSupplierRow.setText(supplierArrayList.get(position).getCost()  + " ₽");

        Picasso.with(context).load(supplierArrayList.get(position).getImage()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), supplier_info.class);
                intent.putExtra("title", supplierArrayList.get(pos).getTitle());
                intent.putExtra("cost", supplierArrayList.get(pos).getCost());
                intent.putExtra("rating", supplierArrayList.get(pos).getRating());

                intent.putExtra("descr", supplierArrayList.get(pos).getDescription());

                intent.putExtra("image", supplierArrayList.get(pos).getImage());
                context.startActivity(intent);


            }
        });

    }


    public String stringRating(Double rating){
        String ratingText = String.valueOf(rating);
        return  ratingText;
    }

    @Override
    public int getItemCount() {
        return supplierArrayList.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView titleSupplierRow;
        TextView ratingRowSupplier;
        TextView costSupplierRow;

        ImageView image;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            titleSupplierRow = itemView.findViewById(R.id.titleSupplierRow);
            ratingRowSupplier = itemView.findViewById(R.id.ratingRowSupplier);
            costSupplierRow = itemView.findViewById(R.id.costSupplierRow);
            image = itemView.findViewById(R.id.imageSupplierRow);

        }
    }
}
